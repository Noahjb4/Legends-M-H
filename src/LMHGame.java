import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

public class LMHGame implements Game{
    private Party party = new Party();
    private LMHBoard board = new LMHBoard(this.party);
    private String warriorsPath = "./src/Warriors.txt";
    private String sorcerersPath = "./src/Sorcerers.txt";
    private String paladinsPath = "./src/Paladins.txt";
    private String dragonsPath = "./src/Dragons.txt";
    private String exoskeletonsPath = "./src/Exoskeletons.txt";
    private String spiritsPath = "./src/Spirits.txt";
    private String armorPath = "./src/Armory.txt";
    private String weaponsPath = "./src/Weaponry.txt";
    private String potionsPath = "./src/Potions.txt";
    private String fireSpellsPath = "./src/FireSpells.txt";
    private String iceSpellsPath = "./src/IceSpells.txt";
    private String lightningSpellsPath = "./src/LightningSpells.txt";

    ArrayList<String[]> weaponsList = FileManager.readFile(weaponsPath);
    ArrayList<String[]> potionsList = FileManager.readFile(potionsPath);
    ArrayList<String[]> armorList = FileManager.readFile(armorPath);
    ArrayList<String[]> fireSpellList = FileManager.readFile(fireSpellsPath);
    ArrayList<String[]> iceSpellList = FileManager.readFile(iceSpellsPath);
    ArrayList<String[]> lightningSpellList = FileManager.readFile(lightningSpellsPath);


    /**
     * play method to begin game loop sequence for Tic Tac Toe
     */
    @Override
    public void play(){

        System.out.println("Welcome to Legends: Monsters and Heroes!\n");
        partySelection();
        moveSequence();

    }

    /**
     * partSelection method to assign Heroes to the users party
     */
    public void partySelection(){
        ArrayList<String[]> heroes;
        Hero member;
        char heroClass;

        System.out.println("PARTY SELECTION:");

        for(int i = 0; i < this.party.getMAXSIZE(); i++) {

            System.out.println("Choose a class:");
            System.out.println("1. Warrior\n" +
                    "2. Sorcerer\n" +
                    "3. Paladin\n");

            heroClass = InputValidation.validInput(new char[]{'1', '2', '3'});

            if (heroClass == '1') {
                System.out.println("Warriors:");
                heroes = FileManager.readFile(this.warriorsPath);
            } else if (heroClass == '2') {
                System.out.println("Sorcerers:");
                heroes = FileManager.readFile(this.sorcerersPath);
            } else if (heroClass == '3') {
                System.out.println("Paladins:");
                heroes = FileManager.readFile(this.paladinsPath);
            } else{
                break;
            }
            FileManager.printNames(heroes);
            System.out.println();
            System.out.println("Pick A Hero:");

            int heroChoice = InputValidation.validInt(1,heroes.size());
            member = FileManager.heroCreator(heroes.get(heroChoice-1), heroClass);
            this.party.addMember(member);

            System.out.println(member.getName() +" the "+member.getClass().getName()+" added to your party!");
            System.out.println("Party size: "+this.party.getSize()+"/"+this.party.getMAXSIZE()+"\n");

            if(i <this.party.getMAXSIZE()-1) {
                System.out.println("Would you like to add another Hero to your party? [y,n]");
                if (InputValidation.validInput(new char[]{'y', 'n'}) == 'n') {
                    break;
                }
            }
        }

        System.out.println("You're now ready to adventure with the following party:");
        System.out.println(this.party);

    }



    public void moveSequence(){
        while (true) {
            System.out.println(displayMap(true));
            char move = InputValidation.validInput(new char[]{'w', 'a', 's', 'd', 'e', 'i', 'm', 'q'}, true);
            if (move == 'i' || move == 'm'|| move == 'q') {
                constantCommands(move);
            } else if (this.party.getCurCell() instanceof MarketCell && move == 'e'){
                marketSequence();
            } else {
                move(move);
                if (this.party.getCurCell() instanceof CommonCell) {
                    encounter();
                }
            }
        }
    }

    public void move(char m){
        char newMove;
        if(this.board.isValidMove(m)){
            this.party.moveLocation(m);
            this.board.updatePartyLocation();
        } else {
            System.out.println("Not a valid Move");
        }
    }

    public void encounter () {
        Random odds = new Random();
        if(odds.nextInt(CommonCell.battleChance) == 1){
            fightSequence();
        }

    }

    public void marketSequence(){
        char choice;
        int[] maxChoices;
        char[] extraChoices = new char[]{'m','i','r','c','q'};

        while (true) {
            System.out.println("MARKET\n");
            System.out.println("| M/m: Display map \n" +
                    "| I/i: Display info \n" +
                    "| R/r: Reset Shop \n" +
                    "| C/c: Exit Shop \n");
            System.out.println("Would You like to Buy or Sell:");
            System.out.println("1. Buy \n" +
                    "2. Sell \n");
            maxChoices = IntStream.rangeClosed(1,2).toArray();
            choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    continue;
                }else if (choice == 'c'){
                    break;
                }else{
                    constantCommands(choice);
                    continue;
                }
            }
            if (choice == '1') {
                if(marketBuy()){
                    break;
                }
            } else {
                marketSell();
            }
        }
    }

    public boolean marketSell(){
        char choice;
        Hero curHero;
        boolean leaveMarket = true;
        int[] maxChoices;
        char[] extraChoices = new char[]{'m','i','r','c','q'};

        while(true) {
            System.out.println("Choose a Hero to sell with:");
            System.out.println(this.party);
            maxChoices = IntStream.rangeClosed(1,this.party.getSize()).toArray();
            choice = InputValidation.validIntOrChar(Arrays.copyOfRange(maxChoices,0,this.party.getSize()),extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    break;
                }else if (choice == 'c'){
                    return leaveMarket;
                }else{
                    constantCommands(choice);
                    break;
                }
            }

            curHero = this.party.getMember(Character.getNumericValue(choice)-1);
            System.out.println("Select an item Type to sell for "+curHero.getName()+":");
            System.out.println("1. Weapons \n" +
                    "2. Armor \n" +
                    "3. Potions \n");

            maxChoices = IntStream.rangeClosed(1,4).toArray();
            choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    break;
                }else if (choice == 'c'){
                    return leaveMarket;
                }else{
                    constantCommands(choice);
                    break;
                }
            }

            if(choice == '1'){
                curHero.printWeaponInventory();
                System.out.println("\nSelect Weapon to sell for "+curHero.getName()+":");

                if (curHero.getWeaponInventory().size() <1){
                    System.out.println("Nothing to Sell");
                    break;
                }
                maxChoices=IntStream.rangeClosed(1,curHero.getWeaponInventory().size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curHero.remWeapon(choice-'0'-1);
            } else if (choice == '2'){
                System.out.println(FileManager.fileHeader(armorPath));
                FileManager.printFile(this.armorList);
                System.out.println("\nSelect Armor to sell for "+curHero.getName()+":");

                if (curHero.getArmorInventory().size() <1){
                    System.out.println("Nothing to Sell");
                    break;
                }
                maxChoices=IntStream.rangeClosed(1,curHero.getArmorInventory().size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curHero.remArmor(choice-'0'-1);
            } else if (choice == '3'){
                System.out.println(FileManager.fileHeader(potionsPath));
                FileManager.printFile(this.potionsList);
                System.out.println("\nSelect Potion to sell for "+curHero.getName()+":");
                if (curHero.getPotionInventory().size() <1){
                    System.out.println("Nothing to Sell");
                    break;
                }
                maxChoices=IntStream.rangeClosed(1,curHero.getPotionInventory().size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curHero.remPotion(choice-'0'-1);
            }
        }
        leaveMarket = false;
        return leaveMarket;
    }

    public boolean marketBuy(){
        char choice;
        Hero curHero;
        Item curItem;
        boolean leaveMarket = true;
        int[] maxChoices;
        char[] extraChoices = new char[]{'m','i','r','c','q'};


        while(true) {
            System.out.println("Choose a Hero to shop with:");
            System.out.println(this.party);
            maxChoices = IntStream.rangeClosed(1,this.party.getSize()).toArray();
            choice = InputValidation.validIntOrChar(Arrays.copyOfRange(maxChoices,0,this.party.getSize()),extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    break;
                }else if (choice == 'c'){
                    return leaveMarket;
                }else{
                    constantCommands(choice);
                    break;
                }
            }

            curHero = this.party.getMember(Character.getNumericValue(choice)-1);
            System.out.println("Select an item Type to buy for "+curHero.getName()+":");
            System.out.println("1. Weapons \n" +
                    "2. Armor \n" +
                    "3. Potions \n" +
                    "4. Spells \n");

            maxChoices = IntStream.rangeClosed(1,4).toArray();
            choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    break;
                }else if (choice == 'c'){
                    return leaveMarket;
                }else{
                    constantCommands(choice);
                    break;
                }
            }

            if(choice == '1'){
                System.out.println(FileManager.fileHeader(weaponsPath));
                FileManager.printFile(this.weaponsList);
                System.out.println("\n Select Weapon to buy for "+curHero.getName()+":");

                maxChoices=IntStream.rangeClosed(1,weaponsList.size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curItem = FileManager.weaponCreator(this.weaponsList.get(choice-'0'-1));
                if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                    System.out.println("You cannot Purchase this Item!");
                    break;
                }
                curHero.addToInventory(curItem);
                this.weaponsList.remove(choice-'0'-1);
            } else if (choice == '2'){
                System.out.println(FileManager.fileHeader(armorPath));
                FileManager.printFile(this.armorList);
                System.out.println("\n Select Armor to buy for "+curHero.getName()+":");

                maxChoices=IntStream.rangeClosed(1,armorList.size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curItem = FileManager.weaponCreator(this.armorList.get(choice-'0'-1));
                if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                    System.out.println("You cannot Purchase this Item!");
                    break;
                }
                curHero.addToInventory(curItem);
                this.armorList.remove(choice-'0'-1);
            } else if (choice == '3'){
                System.out.println(FileManager.fileHeader(potionsPath));
                FileManager.printFile(this.potionsList);
                System.out.println("\n Select Potion to buy for "+curHero.getName()+":");

                maxChoices=IntStream.rangeClosed(1,potionsList.size()).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                curItem = FileManager.weaponCreator(this.potionsList.get(choice-'0'-1));
                if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                    System.out.println("You cannot Purchase this Item!");
                    break;
                }
                curHero.addToInventory(curItem);
                this.potionsList.remove(choice-'0'-1);
            } else if (choice == '4'){
                System.out.println("Select a spell type:");
                System.out.println("1. Ice Spells\n" +
                        "2. Fire Spells\n" +
                        "3. Lightning Spells\n");
                maxChoices = IntStream.rangeClosed(1,3).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        break;
                    }else if (choice == 'c'){
                        return leaveMarket;
                    }else{
                        constantCommands(choice);
                        break;
                    }
                }
                if(choice == '1'){
                    System.out.println(FileManager.fileHeader(iceSpellsPath));
                    FileManager.printFile(this.iceSpellList);
                    System.out.println("\n Select Ice Spell to buy for "+curHero.getName()+":");

                    maxChoices=IntStream.rangeClosed(1,iceSpellList.size()).toArray();
                    choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                    if(Character.isAlphabetic(choice)){
                        if (choice == 'r'){
                            break;
                        }else if (choice == 'c'){
                            return leaveMarket;
                        }else{
                            constantCommands(choice);
                            break;
                        }
                    }
                    curItem = FileManager.weaponCreator(this.iceSpellList.get(choice-'0'-1));
                    if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                        System.out.println("You cannot Purchase this Item!");
                        break;
                    }
                    curHero.addToInventory(curItem);
                    this.iceSpellList.remove(choice-'0'-1);
                } else if (choice == '2'){
                    System.out.println(FileManager.fileHeader(fireSpellsPath));
                    FileManager.printFile(this.fireSpellList);
                    System.out.println("\n Select Weapon to buy for "+curHero.getName()+":");

                    maxChoices=IntStream.rangeClosed(1,fireSpellList.size()).toArray();
                    choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                    if(Character.isAlphabetic(choice)){
                        if (choice == 'r'){
                            break;
                        }else if (choice == 'c'){
                            return leaveMarket;
                        }else{
                            constantCommands(choice);
                            break;
                        }
                    }
                    curItem = FileManager.weaponCreator(this.fireSpellList.get(choice-'0'-1));
                    if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                        System.out.println("You cannot Purchase this Item!");
                        break;
                    }
                    curHero.addToInventory(curItem);
                    this.fireSpellList.remove(choice-'0'-1);
                } else if (choice == '3'){
                    System.out.println(FileManager.fileHeader(lightningSpellsPath));
                    FileManager.printFile(this.lightningSpellList);
                    System.out.println("\n Select Weapon to buy for "+curHero.getName()+":");

                    maxChoices=IntStream.rangeClosed(1,lightningSpellList.size()).toArray();
                    choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                    if(Character.isAlphabetic(choice)){
                        if (choice == 'r'){
                            break;
                        }else if (choice == 'c'){
                            return leaveMarket;
                        }else{
                            constantCommands(choice);
                            break;
                        }
                    }
                    curItem = FileManager.weaponCreator(this.lightningSpellList.get(choice-'0'-1));
                    if(curHero.getLevel() < curItem.getRequiredLevel() || curHero.getMoney() < curItem.getCost()){
                        System.out.println("You cannot Purchase this Item!");
                        break;
                    }
                    curHero.addToInventory(curItem);
                    this.lightningSpellList.remove(choice-'0'-1);
                }

            }

        }
        leaveMarket = false;
        return leaveMarket;
    }


    public void fightSequence(){
        MonsterParty monsters;
        monsters = populateMonsters();
        Hero curHero;
        Monster curMonster;
        char choice;
        int[] maxChoices;
        char[] extraChoices = new char[]{'m','i','q'};

        while(!this.party.lostFight() && !monsters.lostFight()) {
            for (int i = 0; i < this.party.getSize(); i++) {
                System.out.println("FIGHT\n");
                curHero = this.party.getMember(i);
                curMonster = monsters.getMember(i);
                System.out.println(curHero.getName() + " VS " + curMonster.getName());
                System.out.println("1. Attack \n");
                maxChoices = IntStream.rangeClosed(1, 4).toArray();
                choice = InputValidation.validIntOrChar(maxChoices, extraChoices, true);
                if (Character.isAlphabetic(choice)) {
                    constantCommands(choice);
                    fightSequence();
                }
                if (choice == '1') {
                    curMonster.decHp(curHero.getStrength());
                    curHero.decHp(curMonster.getDamage());

                    System.out.println(curHero.getName() + " did " + curHero.getStrength() + " Damage");
                    System.out.println(curMonster.getName() + " did " + curMonster.getDamage() + " Damage\n");

                    System.out.println(curHero.getName()+" Has "+curHero.getHp()+" HP remaining");
                    System.out.println(curMonster.getName()+" Has "+curMonster.getHp()+" HP remaining");
                }
            }
        }

        if(this.party.lostFight()){
            System.out.println("YOU LOSE");
            System.exit(0);
        }

        this.party.reviveParty();

    }

    public MonsterParty populateMonsters(){
        ArrayList<String[]> dragons = FileManager.readFile(dragonsPath);
        ArrayList<String[]> exoskeletons = FileManager.readFile(exoskeletonsPath);
        ArrayList<String[]> spirits = FileManager.readFile(spiritsPath);

        MonsterParty monsters = new MonsterParty();
        int type;
        int row;

        Random rand = new Random();
        type = rand.nextInt(4);
        for (int i = 0; i < this.party.getSize(); i++) {
            if(type == 1){
                row = rand.nextInt(dragons.size());
                monsters.addMember(FileManager.monsterCreator(dragons.get(row),'1'));
            } else if(type == 2){
                row = rand.nextInt(exoskeletons.size());
                monsters.addMember(FileManager.monsterCreator(exoskeletons.get(row),'2'));
            } else if(type == 3){
                row = rand.nextInt(spirits.size());
                monsters.addMember(FileManager.monsterCreator(spirits.get(row),'3'));
            }
        }

        return monsters;

    }

    public void constantCommands(char c){
        if(c == 'i'){
            displayStats();
        } else  if(c == 'm'){
            System.out.println(displayMap(false));
        } else  if(c == 'q'){
            System.exit(0);
        }

        System.out.println("Enter any character to continue:");
        InputValidation.validChar();

    }



    public String controls(){
        String cont = "+-----CONTROLS-----+\n" +
                "| W/w: move up     |\n" +
                "| A/a: move left   |\n" +
                "| S/s: move down   |\n" +
                "| D/d: move right  |\n" +
                "| E/e: -> Market   |\n" +
                "| I/i: show info   |\n" +
                "| M/m: show map    |\n" +
                "| Q/q: quit game   |\n" +
                "+------------------+";
        return cont;
    }

    public String displayMap(boolean withControls){
        String map = "";
        String title ="WORLD MAP";
        String[] controls = controls().split("\n");
        String[] board = this.board.toString().split("\n");
        int i = 0;

        while(i <  (((this.board.getWidth()*4)+1)-title.length())/2){
            map += " ";
            i++;
        } i =0;
        map += title+"\n";
        if(withControls){
            while(i<controls.length || i<board.length){
                if(i<board.length) {
                    map += board[i];
                }
                if(i<controls.length){
                    map+="          " + controls[i];
                }
                map += "\n";
                i++;
            }
        } else {
            map += this.board.toString();
        }

        return map;
    }

    public void displayStats(){
        System.out.println(this.party.detailedDisplay());
    }

}
