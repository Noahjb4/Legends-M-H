import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Main --- Program to begin Game Launcher
 * @author Noah Jean-Baptiste
 */
public class Main {
    /**
     * Begins Game Launcher
     * and the command line arguments.
     * @param args A string array containing the command line arguments.
     * @return No return value.
     */
    public static void main(String[] args) throws FileNotFoundException {

//        for (String[] s :FileManager.read("./src/Spirits.txt")) {
//            System.out.println(Arrays.toString(s));
//        }

        GameLauncher.launch();
    }
}
