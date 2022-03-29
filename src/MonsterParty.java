import java.util.ArrayList;

public class MonsterParty {
    ArrayList<Monster> members;
    public MonsterParty(){
        this.members = new ArrayList<Monster>();
    }

    public void addMember(Monster m){
        members.add(m);
    }

    public Monster getMember(int i){
        return this.members.get(i);
    }

    public boolean lostFight(){
        for (Monster m : this.members) {
            if (m.isAlive()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String personnel = "Remaining Monsters: \n";
        int i = 1;
        for (Monster member : this.members) {
            if(member.isAlive) {
                personnel += i + ". " + member + "\n";
                i++;
            }
        }
        return personnel;
    }
}
