import java.util.ArrayList;

public class LMHGame implements Game{
    private LMHBoard board;
    private Party party = new Party();
    private String warriorsPath = "./src/Warriors.txt";
    private String sorcerersPath = "./src/Sorcerers.txt";
    private String paladinsPPath = "./src/Paladins.txt";


    /**
     * play method to begin game loop sequence for Tic Tac Toe
     */
    @Override
    public void play(){

        System.out.println("Welcome to Legends: Monsters and Heroes!\n");
        partySelection();
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
                heroes = FileManager.readFile(this.paladinsPPath);
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

    public void displayStats(){

    }

}
