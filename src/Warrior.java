/**
 * Warrior --- Class that represents a Warrior in a LHMGame
 * @author Noah Jean-Baptiste
 */
public class Warrior extends Hero{


    /**
     * Constructor creates a Warrior with given starting attributes
     *
     * @param n   name of Warrior
     * @param l   level of Warrior
     * @param m   mana of Warrior
     * @param str strength of Warrior
     * @param agi agility of Warrior
     * @param dex dexterity of Warrior
     * @param c   money of Warrior
     * @param xp  experience of Warrior
     */
    public Warrior(String n, int l, int m, int str, int agi, int dex, int c, int xp) {
        super(n, l, m, str, agi, dex, c, xp);
    }

    @Override
    public void incrementLevel() {
        super.incrementLevel();
        this.strength *= 1.1;
        this.agility *= 1.1;
        this.dexterity *= 1.05;

    }
}
