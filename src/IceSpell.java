public class IceSpell extends Spell{
    public IceSpell(String n, int c, int lvlReq, int dmg, int mc){
        super(n, c, lvlReq,dmg,mc);
    }

    @Override
    public void castSpell(Monster m) {
        super.castSpell(m);
        m.setDamage((int)(m.getDamage()*.9));
    }
}
