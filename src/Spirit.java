public class Spirit extends Monster{
    /**
     * Constructor creates a Monster with a name, hp, and level
     *
     * @param n   name of Monster
     * @param l   level of Monster
     * @param dmg damage of Monster
     * @param def defense of Monster
     * @param dc  dodgeChance of Monster
     */
    public Spirit(String n, int l, int dmg, int def, int dc) {
        super(n, l, dmg, def, dc);
    }
}
