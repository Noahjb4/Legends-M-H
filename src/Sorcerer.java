/**
 * Sorcerer --- Class that represents a Sorcerer in a LHMGame
 * @author Noah Jean-Baptiste
 */
public class Sorcerer extends Hero{
    /**
     * Constructor creates a Sorcerer with given starting attributes
     *
     * @param n   name of Sorcerer
     * @param l   level of Sorcerer
     * @param m   mana of Sorcerer
     * @param str strength of Sorcerer
     * @param agi agility of Sorcerer
     * @param dex dexterity of Sorcerer
     * @param c   money of Sorcerer
     * @param xp  experience of Sorcerer
     */
    public Sorcerer(String n, int l, int m, int str, int agi, int dex, int c, int xp) {
        super(n, l, m, str, agi, dex, c, xp);
    }

    @Override
    public void incrementLevel() {
        super.incrementLevel();
        this.strength *= 1.05;
        this.agility *= 1.1;
        this.dexterity *= 1.1;

    }
}
