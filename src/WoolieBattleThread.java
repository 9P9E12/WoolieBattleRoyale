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
	private static SportsComplex sc;

    /** a reference to the sports complex for conducting who battles when */
    private Woolie w1;

    /**
     * Accessor for Fighter 1
     * @return The Woolie that is fighter 1
     */
    public Woolie getW1() {
        return w1;
    }

    /**
     * Accessor for Fighter 2
     * @return The Woolie that is fighter 2
     */
    public Woolie getW2() {
        return w2;
    }

    /** a reference to the sports complex for conducting who battles when */
    private Woolie w2;

	/**
	 * This method is only used for testing.
	 */
	public WoolieBattleThread(int id, SportsComplex sc) {
		this.id = id;
		this.sc = sc;
	}

    public WoolieBattleThread(Woolie fighter1, Woolie fighter2, SportsComplex sc) {
	    w1 = fighter1;
	    w2 = fighter2;
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
		System.out.println("WOOLIES: " + w1 + " and " + w2 + " enterArena line to battle.");
		sc.enterArena();
        System.out.println("WOOLIES: " + w1 + " and " + w2 + " enterArena arena to battle.");
        while(w1.isOK() && w2.isOK()){
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
		//Initialize list to store woolies
		ArrayList<Woolie> woolies = new ArrayList<>();
		//Scan document
        try ( Scanner woolieFile = new Scanner( new File(args[0]) )
        ) {
            //Create Sports Complex with specified amount usable at once
            int maxInUse = woolieFile.nextInt();
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
        troll.beginBattleRoyale();
        System.out.println("main ends!");
	}
}
