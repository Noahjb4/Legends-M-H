

public class LMHGame implements Game{
    private LMHBoard board;
    private Party party;


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
        System.out.println("PARTY SELECTION:");
        System.out.println("Choose a class:");
        System.out.println("1. Warrior\n" +
                            "2. Sorcerer\n" +
                            "3. Paladin\n");
        char heroClass = InputValidation.validInput(new char[]{'1','2','3'});

        if (heroClass == '1') {
            System.out.println("Warriors:\n");

        } else if (heroClass == '2') {
            System.out.println("Sorcerers:\n");
        }else if (heroClass == '3') {
            System.out.println("Paladins:\n");
        }

    }

    public void displayStats(){

    }

}
