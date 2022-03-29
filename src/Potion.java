public class Potion extends Item{
    int attributeIncrease;
    String[] affectedAttributes;

    public Potion(String n, int c, int lvlReq, int attInc, String aAtts ){
        super(n, c, lvlReq);
        this.attributeIncrease = attInc;
        this.affectedAttributes = aAtts.split("/");
    }


    /**
     * setAttributeIncrease method sets Attribute increase of a Potion
     * @param ai to become Armor attributeIncrease
     */
    public void setAttributeIncrease(int ai) {
        this.attributeIncrease = ai;
    }

    /**
     * getAttributeIncrease method gets Attribute Increase of a Potion
     * @return attributeIncrease of a Potion
     */
    public int getAttributeIncrease() {
        return this.attributeIncrease;
    }

    /**
     * drinkPotion method applies potion effects to a Hero
     * @param h Hero to apply effects to
     */
    public void drinkPotion(Hero h){
        for (String att : this.affectedAttributes) {
            if(att.equals("Health")){
                h.incHp(this.attributeIncrease);
            } else if(att.equals("Mana")){
                h.setMana(h.getMana()+this.attributeIncrease);
            } else if(att.equals("Strength")){
                h.setStrength(h.getStrength()+this.attributeIncrease);
            } else if(att.equals("Dexterity")){
                h.setDexterity(h.getDexterity()+this.attributeIncrease);
            } else if(att.equals("Defense")){
                h.setDefense(h.getDefense()+this.attributeIncrease);
            } else if(att.equals("Agility")){
                h.setAgility(h.getAgility()+this.attributeIncrease);
            }
        }
    }
}
