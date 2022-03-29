
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

    public static String fileHeader(String filePath){
        try (Scanner s = new Scanner(new File(filePath))){
            String header = s.nextLine();
            return header;
        } catch (FileNotFoundException e){
            return null;
        }
    }

    public static Hero heroCreator(String[] info, char c){
        Hero h;
        String name = info[0].replace('_',' ');
        int lvl = 0;
        int mana = Integer.parseInt(info[1]);
        int str = Integer.parseInt(info[2]);
        int agi = Integer.parseInt(info[3]);
        int dex = Integer.parseInt(info[4]);
        int money = Integer.parseInt(info[5]);
        int xp = Integer.parseInt(info[6]);

        if (c == '1') {
            h = new Warrior(name,lvl,mana,str,agi,dex,money,xp);
        } else if (c == '2') {
            h = new Sorcerer(name,lvl,mana,str,agi,dex,money,xp);
        } else if (c == '3') {
            h = new Paladin(name,lvl,mana,str,agi,dex,money,xp);
        } else{
            System.out.println("Not a valid class");
            return null;
        }

        return h;
    }

    public static Spell spellCreator(String[] info, char c){
        Spell s;
        String name = info[0].replace('_',' ');
        int cost = Integer.parseInt(info[1]);
        int lvlReq = Integer.parseInt(info[2]);
        int dmg = Integer.parseInt(info[3]);
        int mc = Integer.parseInt(info[4]);

        if (c == '1') {
            s = new IceSpell(name,cost,lvlReq,dmg,mc);
        } else if (c == '2') {
            s = new FireSpell(name,cost,lvlReq,dmg,mc);
        } else if (c == '3') {
            s = new LightningSpell(name,cost,lvlReq,dmg,mc);
        } else{
            System.out.println("Not a valid Spell");
            return null;
        }

        return s;
    }

    public static Weapon weaponCreator(String[] info){
        Weapon w;
        String name = info[0].replace('_',' ');
        int cost = Integer.parseInt(info[1]);
        int lvlReq = Integer.parseInt(info[2]);
        int dmg = Integer.parseInt(info[3]);
        w = new Weapon(name, cost, lvlReq, dmg );
        return w;
    }

    public static Armor armorCreator(String[] info){
        Armor a;
        String name = info[0].replace('_',' ');
        int cost = Integer.parseInt(info[1]);
        int lvlReq = Integer.parseInt(info[2]);
        int dmgRed = Integer.parseInt(info[3]);
        a = new Armor(name, cost, lvlReq, dmgRed );
        return a;
    }

    public static Potion potionCreator(String[] info){
        Potion p;
        String name = info[0].replace('_',' ');
        int cost = Integer.parseInt(info[1]);
        int lvlReq = Integer.parseInt(info[2]);
        int effect = Integer.parseInt(info[3]);
        String affectedAtt = info[3];
        p = new Potion(name, cost, lvlReq, effect, affectedAtt);
        return p;
    }
}
