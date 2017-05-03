import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Troll
 * @author Justin Goodchild
 */
public class Troll {
    //Used to seed the Random Woolie attack value. I'm assuming this is for debugging purposes
    private static int seed /*= 1*/;

    // The list of woolies alive
    private ArrayList<Woolie> woolies;

    // The sports complex to use
    private SportsComplex complex;

    /**
     * The Troll Constructor
     * @param woolies The list of Woolies that will battle
     * @param sportsComplex The sports complex the battle will occur in
     */
    public Troll(ArrayList<Woolie> woolies, SportsComplex sportsComplex){
        this.woolies = woolies;
        this.complex = sportsComplex;
    }

    /**
     * This tells the troll to begin the Battle Royale with the current list of Woolies
     * @param round The current round number, used with system.out statements
     */
    public synchronized void beginBattleRoyale(Object round){
        //Check if a round number was specified. If not, round = 1
        round = round != null ? round : 1;
        //Interpret round as an integer
        int roundNum = (Integer) round;
        //Output that the round is going to begin
        System.out.println("Round " + roundNum + " is about to begin!" +
                "\nThe contestants for this round are:");
        //Output all the woolies for the current round
        for (Woolie woolie: woolies) {
            System.out.println("\t" + woolie);
        }
        //Did you just give me one woolie?
        if (woolies.size() != 1){
            System.out.println("\n");
            Random pickTwo = new Random();
            //pickTwo.setSeed(seed);
            //Initialize list of surviving woolies and list of threads
            ArrayList<WoolieBattleThread> threads = new ArrayList<>();
            ArrayList<Woolie> winners = new ArrayList<>();
            //Even or odd amount of woolies?
            if(woolies.size() % 2 == 0){
                //Even amount? Just separate them by two and eventually we'll get them all
                while (!woolies.isEmpty()){
                    Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie1);
                    Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie2);
                    WoolieBattleThread battleThread = new WoolieBattleThread(Woolie1, Woolie2, complex);
                    battleThread.start();
                    threads.add(battleThread);
                }
            } else {
                for (int i = 0; i < woolies.size()-1 ; i++) {
                    //Same routine, but we need to account for the odd man out
                    Woolie Woolie1 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie1);
                    Woolie Woolie2 = woolies.get(pickTwo.nextInt(woolies.size()));
                    woolies.remove(Woolie2);
                    WoolieBattleThread battleThread = new WoolieBattleThread(Woolie1, Woolie2, complex);
                    battleThread.start();
                    threads.add(battleThread);
                }
                //odd man out gets to advance straight to winners
                Woolie oddman = woolies.get(woolies.size()-1);
                winners.add(oddman);
            }
            //Threads and things
            for (WoolieBattleThread thread: threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Get the winner of each thread
            for (WoolieBattleThread thread: threads) {
                winners.add(thread.getWinner());
            }
            //Declare the round is over
            System.out.println("Round " + roundNum + " has ended!\n");
            System.out.println("The contestants left after this round are:");
            //Output surviving woolies
            for (Woolie winner: winners) {
                System.out.println("\t" + winner);
            }
            //More than one woolie? Back to fighting
            if (winners.size() > 1){
                woolies = winners;
                beginBattleRoyale (roundNum + 1);
                //Just one Woolie? Then he wins!
            } else if (winners.size() == 1){
                System.out.println("\nThe winner is " + winners.get(0).getName());
            } else {
                //I don't think it is possible to hit this statement, but I have it included just to be safe
                System.out.println("\nSomehow all the combatants have managed to kill each other at the same time.");
            }
            //You gave me a single woolie and thought I wouldn't know
        } else {
            System.out.println("\n The winner is " + woolies.get(0).getName());
        }
    }
}
