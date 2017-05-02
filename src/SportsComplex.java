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
        while (currUsedArenas == maxArenas){
            try {
                System.out.println("WOOLIES: " + t.getFighter1().getName() + " and " + t.getFighter2().getName() +
                        " enterArena line to battle.");
                t.wait();
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
    public synchronized void leaveArena(){
        currUsedArenas--;
        notifyAll();
    }
}
