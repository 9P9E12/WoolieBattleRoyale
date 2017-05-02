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
                t.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
