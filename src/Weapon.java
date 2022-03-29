public class Weapon extends Item{
    int damage;

    public Weapon(String n, int c, int lvlReq, int dmg){
        super(n, c, lvlReq);
        this.damage = dmg;
    }


    /**
     * setContents method sets contents of Item
     * @param dmg to become Armor damage
     */
    public void setDamage(int dmg) {
        this.damage = dmg;
    }

    /**
     * getdamage method gets Damage Reduction of Armor
     * @return damage Armor
     */
    public int getDamage() {
        return this.damage;
    }
}
