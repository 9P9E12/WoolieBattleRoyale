import java.util.ArrayList;

/**
 * Represents a Troll
 * @author Justin Goodchild
 */
public class Troll {
    //Used to seed the Random Woolie attack value. I'm assuming this is for debugging purposes
    private static int seed = 1901;

    private ArrayList<Woolie> woolies;

    private int roundNum;

    private SportsComplex complex;

    public Troll(ArrayList<Woolie> woolies, SportsComplex sportsComplex){
        this.woolies = woolies;
        this.complex = sportsComplex;
        roundNum = 1;
    }

    public void beginBattleRoyale(){
        //TODO
        System.out.println("Round " + roundNum + " is about to begin!" +
                "\nThe contestants for this round are:");
        for (Woolie woolie: woolies) {
            System.out.println("\t" + woolie + "\n");
        }
    }
}
