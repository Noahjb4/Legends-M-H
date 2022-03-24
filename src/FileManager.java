
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileManager {

    public static ArrayList<String[]> read(String filePath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        s.nextLine();
        ArrayList<String[]> file = new ArrayList<String[]>();
        while (s.hasNextLine()){
            String[] line = s.nextLine().split("\\s+");
            if(line[0].equals("")){
                break;
            }
            file.add(line);
        }
        s.close();

        return file;
    }
}
