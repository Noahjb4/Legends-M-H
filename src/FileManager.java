
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileManager {

    public static ArrayList<String[]> readFile(String filePath) {
        try (Scanner s = new Scanner(new File(filePath))){
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
        } catch (FileNotFoundException e){
            return null;
        }

    }

    public static void printFile(ArrayList<String[]> file){
        for (int i = 0; i<file.size(); i++) {
            System.out.print((i+1)+". ");
            for (String s : file.get(i)) {
                System.out.print(s +" ");
            }
            System.out.println();

        }
    }

    public static void printNames(ArrayList<String[]> file){
        ArrayList<String[]> temp = new ArrayList<String[]>();

        for (int i = 0; i < file.size(); i++) {
            temp.add(i, new String[]{file.get(i)[0].replace('_', ' ')});
        }
        printFile(temp);
    }
}
