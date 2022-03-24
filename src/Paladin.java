/**
 * Paladin --- Class that represents a Paladin in a LHMGame
 * @author Noah Jean-Baptiste
 */
public class Paladin extends Hero{
    /**
     * Constructor creates a Paladin with given starting attributes
     *
     * @param n   name of Paladin
     * @param l   level of Paladin
     * @param m   mana of Paladin
     * @param str strength of Paladin
     * @param agi agility of Paladin
     * @param dex dexterity of Paladin
     * @param c   money of Paladin
     * @param xp  experience of Paladin
     */
    public Paladin(String n, int l, int m, int str, int agi, int dex, int c, int xp) {
        super(n, l, m, str, agi, dex, c, xp);
    }

    @Override
    public void incrementLevel() {
        super.incrementLevel();
        this.strength *= 1.1;
        this.agility *= 1.05;
        this.dexterity *= 1.1;

    }
}
