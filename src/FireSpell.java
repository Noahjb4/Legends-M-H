public class FireSpell extends Spell{
    public FireSpell(String n, int c, int lvlReq, int dmg, int mc){
        super(n, c, lvlReq,dmg,mc);
    }

    @Override
    public void castSpell(Monster m) {
        super.castSpell(m);
        m.setDefense((int)(m.getDefense()*.9));
    }
}
