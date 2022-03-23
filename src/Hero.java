/**
 * Hero --- Abstract Class that represents a Hero in a LHMGame
 * @author Noah Jean-Baptiste
 */
public abstract class Hero extends GameCharacter{
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected int money;
    protected int experience;


    /**
     * Constructor creates a Hero with a name, hp, and level
     * @param n name of Hero
     * @param l level of Hero
     * @param m mana of Hero
     * @param str strength of Hero
     * @param agi agility of Hero
     * @param dex dexterity of Hero
     * @param c money of Hero
     * @param xp experience of Mosnter
     */
    public Hero(String n, int l, int m, int str, int agi, int dex, int c, int xp){
        super(n,l);

        this.mana = m;
        this.strength = str;
        this.agility = agi;
        this.dexterity = dex;
        this.money = c;
        this.experience = xp;
    }

    /**
     * getMana method gets mana a Monster has
     * @return mana of Monster
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * setDamage method sets mana for Monster
     * @param m number Monster mana should be set to
     */
    public void setMana(int m) {
        this.mana = m;
    }

    /**
     * getStrength method gets strength a Monster has
     * @return strength of Monster
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * setStrength method sets strength for Monster
     * @param str number Monster strength should be set to
     */
    public void setStrength(int str) {
        this.strength = str;
    }

    /**
     * getAgility method gets damage a Monster has
     * @return strength of Monster
     */
    public int getAgility() {
        return agility;
    }

    /**
     * setAgility method sets damage for Monster
     * @param agi number Monster agility should be set to
     */
    public void setAgility(int agi) {
        this.agility = agi;
    }

    /**
     * getDexterity method gets dexterity a Monster has
     * @return dexterity of Monster
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * setDexterity method sets dexterity for Monster
     * @param dex number Monster dexterity should be set to
     */
    public void setDexterity(int dex) {
        this.dexterity = dex;
    }

    /**
     * getMoney method gets money a Monster has
     * @return money of Monster
     */
    public int getMoney() {
        return money;
    }

    /**
     * setMoney method sets money for Monster
     * @param m number Monster money should be set to
     */
    public void setMoney(int m) {
        this.money = money;
    }

    /**
     * incMoney method increases money for GameCharacter
     * @param gain number of money a character will gain
     */
    public void incMoney(int gain) {
        this.money += gain;
    }

    /**
     * decMoney method Decreases money for GameCharacter
     * @param loss number of money a character will lose
     */
    public void decMoney(int loss) {
        this.money -= loss;
    }

    /**
     * getExperience method gets money a Monster has
     * @return experience of Monster
     */
    public int getExperience() {
        return experience;
    }

    /**
     * setMoney method sets experience for Monster
     * @param exp number Monster experience should be set to
     */
    public void setExperience(int exp) {
        this.experience = exp;
    }

    /**
     * incExperience method increases money for GameCharacter
     * @param gain number of money a character will gain
     */
    public void incExperience(int gain) {
        this.experience += gain;

        if(this.experience >= (this.level*10) ){
            this.experience = 0;
            this.incrementLevel();
        }

    }
}
