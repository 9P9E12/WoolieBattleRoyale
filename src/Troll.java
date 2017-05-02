import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Troll
 * @author Justin Goodchild
 */
public class Troll {
    //Used to seed the Random Woolie attack value. I'm assuming this is for debugging purposes
    private static int seed = 1;

    private ArrayList<Woolie> woolies;

    private SportsComplex complex;

    public Troll(ArrayList<Woolie> woolies, SportsComplex sportsComplex){
        this.woolies = woolies;
        this.complex = sportsComplex;
    }

    public void beginBattleRoyale(Object round){
        round = round != null ? round : 1;
        int roundNum = (Integer) round;
        System.out.println("Round " + roundNum + " is about to begin!" +
                "\nThe contestants for this round are:");
        for (Woolie woolie: woolies) {
            System.out.println("\t" + woolie);
        }
        System.out.println("\n");
        Random pickTwo = new Random(seed);
        ArrayList<WoolieBattleThread> threads = new ArrayList<>();
        while (!woolies.isEmpty()){
            Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
            woolies.remove(Woolie1);
            Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
            woolies.remove(Woolie2);
            threads.add(new WoolieBattleThread(Woolie1, Woolie2, complex));
        }
        ArrayList<Woolie> winners = new ArrayList<>();
        for (WoolieBattleThread thread: threads) {
            thread.run();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (WoolieBattleThread thread: threads) {
            winners.add(thread.getWinner());
        }
        if (winners.size() > 1){
            Troll nxtRound = new Troll(winners, complex);
            nxtRound.beginBattleRoyale (roundNum + 1);
        }
    }
}
