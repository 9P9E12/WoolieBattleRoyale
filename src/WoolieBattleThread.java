import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Not needed for now, but do later
 */
class WoolieBattleThread extends Thread {
	/** this is only used for testing */
	private int id;

    /** a reference to the sports complex for conducting who battles when */
	private SportsComplex sc;

    /** a reference to the sports complex for conducting who battles when */
    private Woolie fighter1;

    /** a reference to the sports complex for conducting who battles when */
    private Woolie fighter2;

    /**
     * Accessor for Fighter 1
     * @return The Woolie that is fighter 1
     */
    public Woolie getFighter1() {
        return fighter1;
    }

    /**
     * Accessor for Fighter 2
     * @return The Woolie that is fighter 2
     */
    public Woolie getFighter2() {
        return fighter2;
    }

	/**
	 * This method is only used for testing.
	 */
	public WoolieBattleThread(int id, SportsComplex sc) {
		this.id = id;
		this.sc = sc;
	}

    public WoolieBattleThread(Woolie fighter1, Woolie fighter2, SportsComplex sc) {
	    this.fighter1 = fighter1;
	    this.fighter2 = fighter2;
	    this.sc = sc;
    }

	@Override
	public String toString() {
		return "Battle-" + this.id;
	}

	/**
     * Not needed for now, but do later
	 */
	public void run() {
		System.out.println("WOOLIES: " + fighter1 + " and " + fighter2 + " enterArena line to battle.");
		sc.enterArena(this);
        System.out.println("WOOLIES: " + fighter1 + " and " + fighter2 + " enterArena arena to battle.");
        while(fighter1.isOK() && fighter2.isOK()){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sc.leaveArena();
        System.out.println(toString() + " finishing...");
    }

	/**
     * Not needed for now, but do later
	 */
	public static void main(String[] args) {
        System.out.println("main begins!");

        // create the sports complex containing 2 arenas
        SportsComplex sc = new SportsComplex(2);

        ArrayList<WoolieBattleThread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            WoolieBattleThread tempThread = new WoolieBattleThread(i, sc);
            threads.add(tempThread);
            tempThread.start();
        }

        for (int i=0; i<10; i++) {
            WoolieBattleThread tempThread = threads.get(i);
            try {
                tempThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("main ends!");
    }
}
