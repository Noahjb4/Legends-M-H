/**
 * Item --- Abstract class that represents a game Item
 * @author Noah Jean-Baptiste
 */

public abstract class Item {
    protected String name;
    protected int cost;
    protected int RequiredLevel;

    /**
     * Constructor creates an Item with a name
     * @param n name of Item
     */
    public Item(String n, int c, int reqLvl){
        this.name = n;
        this.cost = c;
        this.RequiredLevel = reqLvl;
    }

    /**
     * No-Arg Constructor creates a Item with default no name
     */
    public Item (){
        this("",-1,-1);
    }

    /**
     * getName method gets name of Item
     * @return name of Item
     */
    public String getName() {
        return name;
    }

    /**
     * setName method set name of Item
     * @param name string to change name to
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * setCost method sets Cost of Item
     * @param c int to become Item Cost
     */
    public void setCost(int c) {
        this.cost = cost;
    }

    /**
     * getCost method gets Cost of Item
     * @return cost Item
     */
    public int getCost() {
        return this.cost;
    }


    /**
     * setRequiredLevel method sets Required Level of an Item
     * @param reqLvl Object to become the required Level
     */
    public void setRequiredLevel(int reqLvl) {
        this.RequiredLevel = reqLvl;
    }

    /**
     * getRequiredLevel method gets required level of Item
     * @return RequiredLevel Item
     */
    public int getRequiredLevel() {
        return this.RequiredLevel;
    }
    /**
     * toString method returns a string representation of Item
     * @return name of Item or default (unnamed Item)
     */
    @Override
    public String toString() {
        if (this.name != "") {
            return name;
        }
        return "(Unnamed Item)";
    }

    /**
     * equals method determines whether two Items are equivalent
     * @param other	other Item object that this is compared to
     * @return true of they have the same name, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this.name.equals(((Item)other).getName())){
            return true;
        }
        return false;
    }


}
