/**
 * GameCharacter --- Abstract class that represents a GameCharacter in a Game
 * @author Noah Jean-Baptiste
 */

public abstract class GameCharacter {
    protected int hp;
    protected int level;
    protected String name;

    /**
     * Constructor creates a GameCharacter with a name, hp, and level
     * @param n name of GameCharacter
     * @param l level of GameCharacter
     */
    public GameCharacter(String n, int l){
        this.level = l;
        this.hp = 100*l;
        this.name = n;
    }

    /**
     * No-Arg Constructor creates a GameCharacter with default level, hp, and name
     */
    public GameCharacter(){
        this.level = 1;
        this.hp = 100;
        this.name = "";
    }

    /**
     * getHp method gets hp a GameCharacter has
     * @return hp of GameCharacter
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * setHp method sets hp for GameCharacter
     * @param level number GameCharacter level should be set to
     */
    public void setHp(int level) {
        this.hp = hp;
    }

    /**
     * incHp method increases hp for GameCharacter
     * @param gain number of hp a character will gain
     */
    public void incHp(int gain) {
        this.hp += gain;
    }

    /**
     * decHp method Decreases hp for GameCharacter
     * @param loss number of hp a character will lose
     */
    public void decHp(int loss) {
        this.hp -= loss;
    }

    /**
     * getLevel method gets level a GameCharacter has
     * @return level of GameCharacter
     */
    public int getLevel() {
        return level;
    }

    /**
     * setLevel method sets level for GameCharacter
     * @param level number GameCharacter level should be set to
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * incrementLevel method increases level for GameCharacter by 1
     */
    public void incrementLevel() {
        this.level++;
    }

    /**
     * getName method gets name of a GameCharacter
     * @return name of GameCharacter
     */
    public String getName() {
        return name;
    }

    /**
     * setName method sets name of a GameCharacter
     * @param name string GameCharacter should be renamed to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString method returns a string representation of a GameCharacter
     * @return name of GameCharacter and level they have
     */
    public String toString() {
        return name + ": \n level = "+level;
    }

    /**
     * equals method determines whether two Characters are equivalent
     * @param other	other GameCharacter that a GameCharacter is compared to
     * @return true of they have the same name and level, false otherwise
     */
    public boolean equals(GameCharacter other) {
        if(this.name != other.getName() || this.level != other.getLevel()){
            return false;
        }

        return true;
    }

}
