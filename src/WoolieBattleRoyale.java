import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Justin Goodchild
 */
public class WoolieBattleRoyale {

    //Sports Complex, used to reference the sports complex outside if the
    private static SportsComplex sc;

    /**
     * Main function to simulate a Battle Royale.
     * Reads the file
     * Makes a list of Woolies to battle
     * Makes a sports complex
     * Creates a troll and tells the troll to start the battle royale.
     * @param args not used (save for the first one)
     */
    public static void main(String[] args){
        //Initialize list to store woolies
        ArrayList<Woolie> woolies = new ArrayList<>();
        //Initialize max amount of arenas available to be used at once
        int maxInUse;
        //Scan document
        try ( Scanner woolieFile = new Scanner( new File(args[0]) )
        ) {
            //Create Sports Complex with specified amount usable at once
            maxInUse = woolieFile.nextInt();
            woolieFile.nextLine();
            sc = new SportsComplex(maxInUse);
            //Create woolies from the remaining lines
            while (woolieFile.hasNext()){
                String curr_line = woolieFile.nextLine();
                String[] stats = curr_line.split(",");
                woolies.add(new Woolie(stats));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Troll troll = new Troll(woolies, sc);
        troll.beginBattleRoyale(null);
    }
}
