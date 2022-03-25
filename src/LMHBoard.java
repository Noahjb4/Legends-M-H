import java.util.*;
import java.util.random.RandomGenerator;

/**
 * LMHBoard --- Class that represents a board for the Legends: Monsters and Heroes Game
 * @author Noah Jean-Baptiste
 */

public class LMHBoard extends Board{
    private Party party;
    private final String[] CELLTYPES = new String[]{"Common", "Market", "Inaccessible" };
    public LMHBoard(Party p) {
        super(8,8);
        this.party = p;

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                randomCell(i,j);
            }
        }
        this.tiles[this.party.getRow()][this.party.getCol()] = new Cell("P");

    }

    public void randomCell(int row, int col){
        Random r = new Random();
        String choice = CELLTYPES[r.nextInt(3)];
        if (choice.equals("Common")){
            this.tiles[row][col] = new CommonCell();
        } else if (choice.equals("Market")){
            this.tiles[row][col] = new MarketCell();
        } else if (choice.equals("Inaccessible")){
            this.tiles[row][col] = new InaccessibleCell();
        }
    }
}
