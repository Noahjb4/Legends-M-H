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
            member = heroCreator(heroes.get(heroChoice-1), heroClass);
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

    public Hero heroCreator(String[] info, char c){
        Hero h;
        String name = info[0].replace('_',' ');
        int lvl = 0;
        int mana = Integer.parseInt(info[1]);
        int str = Integer.parseInt(info[2]);
        int agi = Integer.parseInt(info[3]);
        int dex = Integer.parseInt(info[4]);
        int money = Integer.parseInt(info[5]);
        int xp = Integer.parseInt(info[6]);

        if (c == '1') {
            h = new Warrior(name,lvl,mana,str,agi,dex,money,xp);
        } else if (c == '2') {
            h = new Sorcerer(name,lvl,mana,str,agi,dex,money,xp);
        } else if (c == '3') {
            h = new Paladin(name,lvl,mana,str,agi,dex,money,xp);
        } else{
            System.out.println("Not a valid class");
            return null;
        }

        return h;
    }

    public void moveSequence(){
        while (true) {
            System.out.println(displayMap(true));
            char move = InputValidation.validInput(new char[]{'w', 'a', 's', 'd', 'i', 'm', 'q'}, true);
            if (move == 'i') {

            } else if (move == 'm') {

            } else if (move == 'q') {

            } else {
                move(move);
                encounter();
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
        if (this.party.getCurCell() instanceof MarketCell){
            marketSequence();
        } else if (this.party.getCurCell() instanceof CommonCell) {
            Random odds = new Random();
            if(odds.nextInt(CommonCell.battleChance) == 1){
                fightSequence();
            }
        } else {
            System.out.println("Error: Invalid Move");
            System.exit(1);
        }
    }

    public void marketSequence(){
        char choice;
        Hero curHero;
        int[] maxChoices;
        char[] extraChoices = new char[]{'m','i','r','c','q'};


        while(true) {
            System.out.println("MARKET\n");
            System.out.println("| M/m: Display map \n" +
                               "| I/i: Display info \n" +
                               "| R/r: Reset Shop \n" +
                               "| C/c: Exit Shop \n");
            System.out.println("Choose a Hero to shop with:");
            System.out.println(this.party);

            maxChoices = IntStream.rangeClosed(1,this.party.getSize()).toArray();
            choice = InputValidation.validIntOrChar(Arrays.copyOfRange(maxChoices,0,this.party.getSize()),extraChoices,true);
            if(Character.isAlphabetic(choice)){
                if (choice == 'r'){
                    continue;
                }else if (choice == 'c'){
                    break;
                }else{
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
                    continue;
                }else if (choice == 'c'){
                    break;
                }else{
                }
            }

            if(choice == '1'){
                FileManager.printNames(this.weaponsList);
            } else if (choice == '2'){
                FileManager.printNames(this.armorList);
            } else if (choice == '3'){
                FileManager.printNames(this.potionsList);
            } else if (choice == '4'){
                System.out.println("Select a spell type:");
                System.out.println("1. Fire Spells\n" +
                        "2. Ice Spells\n" +
                        "3. Lightning Spells\n");
                maxChoices = IntStream.rangeClosed(1,3).toArray();
                choice = InputValidation.validIntOrChar(maxChoices,extraChoices,true);
                if(Character.isAlphabetic(choice)){
                    if (choice == 'r'){
                        continue;
                    }else if (choice == 'c'){
                        break;
                    }else{
                    }
                }
                if(choice == '1'){
                    FileManager.printNames(this.fireSpellList);
                } else if (choice == '2'){
                    FileManager.printNames(this.iceSpellList);
                } else if (choice == '3'){
                    FileManager.printNames(this.lightningSpellList);
                }

            }



        }
    }


    public void fightSequence(){
        System.out.println("FIGHT");
    }



    public String controls(){
        String cont = "+-----CONTROLS-----+\n" +
                "| W/w: move up     |\n" +
                "| A/a: move left   |\n" +
                "| S/s: move down   |\n" +
                "| D/d: move right  |\n" +
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

    }

}
