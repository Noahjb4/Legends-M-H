public class Spell extends Item{
    protected int damage;
    protected int manaCost;

    public Spell(String n, int c, int lvlReq, int dmg, int mc){
        super(n, c, lvlReq);
        this.damage = dmg;
        this.manaCost = mc;
    }

    public void castSpell(Monster m){
        m.decHp(this.damage);
    }

}
