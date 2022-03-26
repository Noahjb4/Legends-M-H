public class InaccessibleCell extends Cell{
    public static final String key = "■";
    public InaccessibleCell(){
        super(key);
        super.isAccessible = false;
    }

}
