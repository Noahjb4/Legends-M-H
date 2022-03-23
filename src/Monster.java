/**
 * Monster --- Abstract Class that represents a Monster in a LHMGame
 * @author Noah Jean-Baptiste
 */
public abstract class Monster extends GameCharacter{
    protected int damage;
    protected int defense;
    protected int dodgeChance;

    /**
     * Constructor creates a Monster with a name, hp, and level
     * @param n name of Monster
     * @param l level of Monster
     * @param dmg damage of Monster
     * @param def defense of Monster
     * @param dc dodgeChance of Monster
     */
    public Monster (String n, int l, int dmg, int def, int dc){
        super(n,l);

        this.damage = dmg;
        this.defense = def;
        this.dodgeChance = dc;
    }

    /**
     * getDamage method gets damage a Monster has
     * @return damage of Monster
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * setDamage method sets damage for Monster
     * @param dmg number Monster damage should be set to
     */
    public void setDamage(int dmg) {
        this.damage = dmg;
    }

    /**
     * getDefense method gets defense a Monster has
     * @return defense of Monster
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * setDefense method sets defense for Monster
     * @param def number Monster def should be set to
     */
    public void setDefense(int def) {
        this.defense = def;
    }

    /**
     * getDodgeChance method gets dodge chance a Monster has
     * @return dodgeChance of Monster
     */
    public int getDodgeChance() {
        return this.dodgeChance;
    }

    /**
     * setDodgeChance method sets dodge chance for Monster
     * @param dc number Monster dodge chance should be set to
     */
    public void setDodgeChance(int dc) {
        this.dodgeChance = dc;
    }
}
