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
    protected boolean inParty;


    /**
     * Constructor creates a Hero with given starting attributes
     * @param n name of Hero
     * @param l level of Hero
     * @param m mana of Hero
     * @param str strength of Hero
     * @param agi agility of Hero
     * @param dex dexterity of Hero
     * @param c money of Hero
     * @param xp experience of Hero
     */
    public Hero(String n, int l, int m, int str, int agi, int dex, int c, int xp){
        super(n,l);

        this.mana = m;
        this.strength = str;
        this.agility = agi;
        this.dexterity = dex;
        this.money = c;
        this.experience = xp;
        this.inParty = false;
    }

    /**
     * getMana method gets mana a Hero has
     * @return mana of Hero
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * setDamage method sets mana for Hero
     * @param m number Hero mana should be set to
     */
    public void setMana(int m) {
        this.mana = m;
    }

    /**
     * getStrength method gets strength a Hero has
     * @return strength of Hero
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * setStrength method sets strength for Hero
     * @param str number Hero strength should be set to
     */
    public void setStrength(int str) {
        this.strength = str;
    }

    /**
     * getAgility method gets damage a Hero has
     * @return strength of Hero
     */
    public int getAgility() {
        return agility;
    }

    /**
     * setAgility method sets damage for Hero
     * @param agi number Hero agility should be set to
     */
    public void setAgility(int agi) {
        this.agility = agi;
    }

    /**
     * getDexterity method gets dexterity a Hero has
     * @return dexterity of Hero
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * setDexterity method sets dexterity for Hero
     * @param dex number Hero dexterity should be set to
     */
    public void setDexterity(int dex) {
        this.dexterity = dex;
    }

    /**
     * getMoney method gets money a Hero has
     * @return money of Hero
     */
    public int getMoney() {
        return money;
    }

    /**
     * setMoney method sets money for Hero
     * @param m number Hero money should be set to
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
     * getInParty method returns if Hero is in party or not
     * @return inParty of hero
     */
    public boolean isInParty(){
        return this.inParty;
    }

    /**
     * setInParty method sets inParty member for Hero
     * @param p boolean the inParty is set to
     */
    public void setInParty(boolean p) {
        this.inParty = p;
    }

    /**
     * getExperience method gets money a Hero has
     * @return experience of Hero
     */
    public int getExperience() {
        return experience;
    }

    /**
     * setMoney method sets experience for Hero
     * @param exp number Hero experience should be set to
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

    /**
     * incrementLevel method increases level, hp, and mana appropriately
     */
    public void incrementLevel() {
        this.level++;
        this.hp =  this.level*100;
        this.mana *= 1.1;
    }

    /**
     * revive method sets isAlive to true
     */
    public void revive(){
        this.isAlive = true;
        this.hp = this.level*50;
    }

    @Override
    public String toString() {
        return this.name + " the " +this.getClass().getName();
    }
}
