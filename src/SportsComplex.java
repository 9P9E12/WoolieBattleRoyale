/**
 * A sports complex is a collection of arenas.
 * @author Justin Goodchild
 */
class SportsComplex {

    // The maximum amount of arenas that can be used at once
	private int maxArenas;

	// The current amount of arenas used
	private int currUsedArenas;

    /**
     * The constructor for a Sports Complex
     * @param maxInUse The maximum amount of arenas that can be used at one time
     */
	public SportsComplex(int maxInUse) {
	    //Construction Zone! Wear your Hard Hat!
			maxArenas = maxInUse;
			currUsedArenas = 0;
	}

    /**
     * Battle thread attempts to enterArena an arena.
     * If an arena is available the thread enters the arena and the battle begins.
     * If no arena is available, the threads waits to be notified that an arena is free.
     * Only one battle at a time can request an arena.
     * @param t the battle thread requesting an arena
     */
    public synchronized void enterArena(WoolieBattleThread t){
        //Print that fighter 1 and 2 are in the line
        System.out.println("WOOLIES: " + t.getFighter1().getName() + " and " + t.getFighter2().getName() +
                " enterArena line to battle.");
        //So long as the max amount of arenas being used at once is equal to the currently used arenas, none shall pass
        while (currUsedArenas == maxArenas){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Print that the fighters are ready to fight
        System.out.println("WOOLIES: " + t.getFighter1().getName() + " and " + t.getFighter2().getName()
                + " enterArena arena to battle.");
        //increment arenas used by 1
        currUsedArenas++;
    }

    /**
     *When a battle is complete the battle will
     * notify the arena that the arena is free.
     * The arena will let any battles waiting know
     * Only one battle at a time can exit and arena. there is a free arena.
     * @param t Used in order to reference the winner
     */
    public synchronized void leaveArena(WoolieBattleThread t){
        System.out.println("WOOLIE: " + t.getWinner().getName() + " leaves the arena victorious!");
        currUsedArenas--;
        this.notifyAll();
    }
}
