import java.util.ArrayList;

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

    /** Buy Winrar you freeloader. This just stores whatever wookie won the current battle */
    private Woolie winrar;



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
	public synchronized void run() {
		sc.enterArena(this);
        System.out.println("The battle has begun between " + fighter1.getName() + " and " + fighter2.getName());
        int time = 0;
        while(fighter1.isOK() && fighter2.isOK()){
            try {
                if (time % fighter1.getHitTime() == 0 && fighter1.isOK()){
                    int dmg = fighter1.getAttackAmount();
                    fighter2.takeDamage(dmg);
                    System.out.println(fighter1.getName() + " does " + dmg + " damage to " + fighter2.getName() + ".");
                    System.out.println(fighter2.getName() + " has " + fighter2.getCurrentHP() + " health left.\n");
                }
                if (time % fighter2.getHitTime() == 0 && fighter2.isOK()){
                    int dmg = fighter1.getAttackAmount();
                    fighter1.takeDamage(dmg);
                    System.out.println(fighter2.getName() + " does " + dmg + " damage to " + fighter1.getName() + ".");
                    System.out.println(fighter1.getName() + " has " + fighter1.getCurrentHP() + " health left.\n");
                }
                time += 1;/*
                System.out.println("DEBUG: Current Time Value - " + time);
                System.out.println("DEBUG: Current Health Values | " + fighter1.getCurrentHP()
                        + " - " + fighter2.getCurrentHP() + " |");
                System.out.println("DEBUG: Current Attack Delays | " + fighter1.getHitTime()
                        + " - " + fighter2.getHitTime() + " |");*/
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The battle is over!");
        if (fighter1.isOK()){
            winrar = fighter1;
        } else {
            winrar = fighter2;
        }
        System.out.println(winrar.getName() + " is the winner!\n");
        winrar.reset();
        sc.leaveArena(this);
    }

    public Woolie getWinner(){
	    return winrar;
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
