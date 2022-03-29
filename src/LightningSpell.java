public class LightningSpell extends Spell{
    public LightningSpell(String n, int c, int lvlReq, int dmg, int mc){
        super(n, c, lvlReq,dmg,mc);
    }

    @Override
    public void castSpell(Monster m) {
        super.castSpell(m);
        m.setDodgeChance((int)(m.getDodgeChance()*.9));
    }
}
