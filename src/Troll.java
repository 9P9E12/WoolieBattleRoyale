import java.util.ArrayList;
import java.util.Random;

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
        System.out.println("Round " + roundNum + " is about to begin!" +
                "\nThe contestants for this round are:");
        for (Woolie woolie: woolies) {
            System.out.println("\t" + woolie + "\n");
        }
        Random pickTwo = new Random(seed);
        Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
        woolies.remove(Woolie1);
        Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
        woolies.remove(Woolie2);
        WoolieBattleThread thread = new WoolieBattleThread(Woolie1, Woolie2, complex);
    }
}
