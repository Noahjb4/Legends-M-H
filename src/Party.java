/**
 * Party --- Abstract Class that represents a Hero in a LHMGame
 * @author Noah Jean-Baptiste
 */
import java.util.*;
public class Party {
    private final int MAXSIZE = 3;
    private ArrayList<Hero> members;
    private int row;
    private int col;
    private Cell curCell;

    public Party() {
        this.members = new ArrayList<Hero>();
        this.curCell = new CommonCell();
    }

    public void addMember(Hero m){
        if(m.isInParty() == true){
            System.out.println("Hero already in party");
        } else if(members.size() >= MAXSIZE){
            System.out.println("Party already full");
        } else{
            members.add(m);
        }

    }

    public void remMember(Hero m){
        if(this.members.size() <= 0){
            System.out.println("Party already empty");
        } else if(!this.members.contains(m)){
            System.out.println("Hero not in Party");
        } else{
            this.members.remove(m);
        }

    }

    public ArrayList<Hero> getMembers() {
        return this.members;
    }

    public Hero getMember(int i){
        return this.members.get(i);
    }

    public int getMAXSIZE() {
        return this.MAXSIZE;
    }

    public int getSize() {
        return this.members.size();
    }

    public int getRow(){
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setLocation (int r, int c) {
        this.row = r;
        this.col = c;
    }

    public void moveLocation(char move) {
        if (move == 'w'){
            this.row -= 1;
        } else if (move == 'a'){
            this.col -= 1;
        } else if (move == 's'){
            this.row += 1;
        } else if (move == 'd'){
            this.col += 1;
        }
    }

    public Cell getCurCell() {
        return this.curCell;
    }

    public void setCurCell(Cell c) {
        this.curCell = c;
    }

    public void winEarnings(int monsterLvl){
        for (Hero h : this.members) {
            if(h.isAlive()) {
                h.setHp(h.getHp() + (h.getHp() / 10));
                h.setMana(h.getMana() + (h.getMana() / 10));
                h.incMoney(monsterLvl * 100);
                h.incExperience(2);
            }
        }
    }

    public void reviveParty() {
        for (Hero h : this.members) {
            if(!h.isAlive()) {
                h.revive();
            }
        }
    }

    public boolean lostFight(){
        for (Hero h : this.members) {
            if (h.isAlive()) {
                return false;
            }
        }

        return true;
    }

    public String detailedDisplay(){
        String personnel = "";
        for (Hero member : this.members) {
            personnel+="-* "+member.getName()+" *-\n"+member.detailedInfo()+"\n";
        }
        return personnel;
    }

    @Override
    public String toString() {
        String personnel = "";
        int i = 1;
        for (Hero member : this.members) {
            personnel+=i+". "+ member +"\n";
            i++;
        }
        return personnel;
    }
}
