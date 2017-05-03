import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Troll
 * @author Justin Goodchild
 */
public class Troll {
    //Used to seed the Random Woolie attack value. I'm assuming this is for debugging purposes
    private static int seed /*= 1*/;

    private ArrayList<Woolie> woolies;

    private SportsComplex complex;

    public Troll(ArrayList<Woolie> woolies, SportsComplex sportsComplex){
        this.woolies = woolies;
        this.complex = sportsComplex;
    }

    public synchronized void beginBattleRoyale(Object round){
        round = round != null ? round : 1;
        int roundNum = (Integer) round;
        System.out.println("Round " + roundNum + " is about to begin!" +
                "\nThe contestants for this round are:");
        for (Woolie woolie: woolies) {
            System.out.println("\t" + woolie);
        }
        if (woolies.size() != 1){
            System.out.println("\n");
            Random pickTwo = new Random();
            //pickTwo.setSeed(seed);
            ArrayList<WoolieBattleThread> threads = new ArrayList<>();
            ArrayList<Woolie> winners = new ArrayList<>();
            if(woolies.size() % 2 == 0){
                while (!woolies.isEmpty()){
                    Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie1);
                    Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie2);
                    threads.add(new WoolieBattleThread(Woolie1, Woolie2, complex));
                }
            } else {
                for (int i = 0; i < woolies.size()-1 ; i++) {
                    Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie1);
                    Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie2);
                    threads.add(new WoolieBattleThread(Woolie1, Woolie2, complex));
                }
                Woolie oddman = woolies.get(woolies.size()-1);
                winners.add(oddman);
            }
            for (WoolieBattleThread thread: threads) {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (WoolieBattleThread thread: threads) {
                winners.add(thread.getWinner());
            }
            System.out.println("Round " + roundNum + " has ended!\n");
            System.out.println("The contestants left after this round are:");
            for (Woolie winner: winners) {
                System.out.println("\t" + winner);
            }
            if (winners.size() > 1){
                Troll nxtRound = new Troll(winners, complex);
                nxtRound.beginBattleRoyale (roundNum + 1);
            } else if (winners.size() == 1){
                System.out.println("\nThe winner is " + winners.get(0).getName());
            } else {
                System.out.println("\nSomehow all the combatants have managed to kill each other at the same time.");
            }
        } else {
            System.out.println("\n The winner is " + woolies.get(0).getName());
        }
    }
}
