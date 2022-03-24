/**
 * Item --- Abstract class that represents a game Item
 * @author Noah Jean-Baptiste
 */

public abstract class Item {
    private String name;

    /**
     * Constructor creates a Item with a name
     * @param n name of Item
     */
    public Item(String n){
        this.name = n;
    }

    /**
     * No-Arg Constructor creates a Item with default no name
     */
    public Item (){
        this("");
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
