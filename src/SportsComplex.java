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

	// TODO: implement enterArena here
    public synchronized void enterArena(){
        while (currUsedArenas == maxArenas){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currUsedArenas++;
    }

	// TODO: implement leaveArena here
    public synchronized void leaveArena(){
        currUsedArenas--;
        notifyAll();
    }
}
