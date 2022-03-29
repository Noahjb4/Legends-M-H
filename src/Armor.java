public class Armor extends Item{
    int damageReduction;

    public Armor(String n, int c, int lvlReq, int dmgRed){
        super(n, c, lvlReq);
        this.damageReduction = dmgRed;
    }


    /**
     * setDamageReduction method sets contents of Item
     * @param dr to become Armor DamageReduction
     */
    public void setDamageReduction(int dr) {
        this.damageReduction = dr;
    }

    /**
     * getDamageReduction method gets Damage Reduction of Armor
     * @return DamageReduction Armor
     */
    public int getDamageReduction() {
        return this.damageReduction;
    }

}
