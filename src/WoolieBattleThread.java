import java.util.ArrayList;

/**
 * Not needed for now, but do later
 */
class WoolieBattleThread extends Thread {
	/** this is only used for testing */
	private int id;

    /** a reference to the sports complex for conducting who battles when */
	private SportsComplex sc;

	/**
	 * This method is only used for testing.
	 */
	public WoolieBattleThread(int id, SportsComplex sc) {
		this.id = id;
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
		System.out.println(toString() + " starting...");
		sc.enterArena();
        System.out.println(toString() + " battling...");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
