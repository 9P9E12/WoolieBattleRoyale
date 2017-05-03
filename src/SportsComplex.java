/**
 * Need to do later
 */
class SportsComplex {

	private int maxArenas;

	private int currUsedArenas;

	/**
	 * need to do later
	 */
	public SportsComplex(int maxInUse) {
			maxArenas = maxInUse;
			currUsedArenas = 0;
	}

    /**
     *
     */
    public synchronized void enterArena(WoolieBattleThread t){
        System.out.println("WOOLIES: " + t.getFighter1().getName() + " and " + t.getFighter2().getName() +
                " enterArena line to battle.");
        while (currUsedArenas == maxArenas){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("WOOLIES: " + t.getFighter1().getName() + " and " + t.getFighter2().getName()
                + " enterArena arena to battle.");
        currUsedArenas++;
    }

    /**
     *
     */
    public synchronized void leaveArena(WoolieBattleThread t){
        System.out.println("WOOLIE: " + t.getWinner().getName() + " leaves the arena victorious!");
        currUsedArenas--;
        this.notifyAll();
    }
}
