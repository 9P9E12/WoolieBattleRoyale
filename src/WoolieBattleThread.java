/**
 * Represents a thread to simulate a battle between two Woolies
 * @author Justin Goodchild
 */
class WoolieBattleThread extends Thread {

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
     * Actual constructor for the woolie battle thread
     * @param fighter1 The first woolie
     * @param fighter2 The second woolie
     * @param sc The sports complex used
     */
    public WoolieBattleThread(Woolie fighter1, Woolie fighter2, SportsComplex sc) {
	    this.fighter1 = fighter1;
	    this.fighter2 = fighter2;
	    this.sc = sc;
    }

    /**
     * Starts the battle between two woolies
     */
	public synchronized void run() {
	    //Attempt to enter the arena
		sc.enterArena(this);
		//Say that the battle's begun
        System.out.println("The battle has begun between " + fighter1.getName() + " and " + fighter2.getName());
        //Initialize counter
        int time = 0;
        //Only stop looping once one of the fighters is dead
        while(fighter1.isOK() && fighter2.isOK()){
            try {
                //A fighter can only attack if the current time is a multiple of his hit delay and he is still alive
                if (time % fighter1.getHitTime() == 0 && fighter1.isOK()){
                    //Declare damage as a variable so it can be stated in the system.out statements
                    int dmg = fighter1.getAttackAmount();
                    //Have the opposite fighter take damage
                    fighter2.takeDamage(dmg);
                    //State that 1 damaged 2
                    System.out.println(fighter1.getName() + " does " + dmg + " damage to " + fighter2.getName() + ".");
                    //State 2's remaining health
                    System.out.println(fighter2.getName() + " has " + fighter2.getCurrentHP() + " health left.\n");
                }
                if (time % fighter2.getHitTime() == 0 && fighter2.isOK()){
                    int dmg = fighter2.getAttackAmount();
                    fighter1.takeDamage(dmg);
                    System.out.println(fighter2.getName() + " does " + dmg + " damage to " + fighter1.getName() + ".");
                    System.out.println(fighter1.getName() + " has " + fighter1.getCurrentHP() + " health left.\n");
                }
                //increment counter
                time += 1;
                //sleep to pass time
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Battle is finished, system.out that
        System.out.println("The battle is over!");
        //Decide who the winner was
        if (fighter1.isOK()){
            winrar = fighter1;
        } else {
            winrar = fighter2;
        }
        //Declare winner
        System.out.println(winrar.getName() + " is the winner!\n");
        //Reset winner's HP
        winrar.reset();
        //Leave
        sc.leaveArena(this);
    }

    /**
     * Accessor for the winner of the battle
     * @return The thread's winner
     */
    public Woolie getWinner(){
	    return winrar;
    }
}
