/**
 * Game --- Interface class represents behavior for game controller
 * @author Noah Jean-Baptiste
 */
public interface Game {
    boolean running = true;

    /**
     * play method to play the Game
     */
    public void play();

    public void moveSequence();

    public String controls();
}
