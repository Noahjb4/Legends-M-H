import java.util.*;
import java.util.random.RandomGenerator;

/**
 * LMHBoard --- Class that represents a board for the Legends: Monsters and Heroes Game
 * @author Noah Jean-Baptiste
 */

public class LMHBoard extends Board{
    private Party party;
    private int prevPartyRow;
    private int prevPartyCol;
    private final String[] CELLTYPES = new String[]{"Common", "Market", "Inaccessible" };
    public LMHBoard(Party p) {
        super(8,8);
        this.party = p;
        this.prevPartyRow = p.getRow();
        this.prevPartyCol = p.getCol();

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

    public void updatePartyLocation(){
        this.tiles[this.party.getRow()][this.party.getCol()] = new Cell("P");
        randomCell(this.prevPartyRow, this.prevPartyCol);
        this.prevPartyRow = this.party.getRow();
        this.prevPartyCol = this.party.getCol();
    }

    public boolean isValidMove(char move){
        boolean valid;
        if (move == 'w'){
            valid = isValidMoveUp();
        } else if (move == 'a'){
            valid = isValidMoveLeft();
        } else if (move == 's'){
            valid = isValidMoveDown();
        } else if (move == 'd'){
            valid = isValidMoveRight();
        } else {
            return false;
        }

        return valid;
    }

    private boolean isValidMoveUp(){
        if (this.prevPartyRow-1 < 0 || this.tiles[this.prevPartyRow-1][this.prevPartyCol].getAccessibility() == false){
            return false;
        }
        return true;
    }
    private boolean isValidMoveRight(){
        if (this.prevPartyCol+1 > this.width-1 || this.tiles[this.prevPartyRow][this.prevPartyCol+1].getAccessibility() == false){
            return false;
        }
        return true;
    }
    private boolean isValidMoveLeft(){
        if (this.prevPartyRow < 0 || this.tiles[this.prevPartyRow][this.prevPartyCol-1].getAccessibility() == false){
            return false;
        }
        return true;
    }
    private boolean isValidMoveDown(){
        if (this.prevPartyRow+1 > this.height-1 || this.tiles[this.prevPartyRow+1][this.prevPartyCol].getAccessibility() == false){
            return false;
        }
        return true;
    }
}
