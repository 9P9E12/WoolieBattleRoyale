import java.util.Random;

/**
 * Represents a Woolie
 * @author Justin Goodchild
 */
public class Woolie {

    //Used to seed the Random Woolie attack value. I'm assuming this is for debugging purposes
    private static int seed = 1901;
    //Name of this Woolie
    private String name;
    //Minimum attack value for this Woolie
    private int minAtk;
    //Maximum attack value for this Woolie
    private int maxAtk;
    //Delay between hits of this Woolie (in milliseconds. Multiply by 1000 for seconds)
    private int hitTime;
    //Maximum HP of this Woolie
    private int maxHP;
    //HP of this woolie
    private int HP;

    /**
     * Accessor for the name
     * @return the name of the woolie
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the minimum attack value of the woolie
     * @return minimum AP for the woolie
     */
    public int getMinAtk() {
        return minAtk;
    }

    /**
     * Accessor for the maximum attack value of the woolie
     * @return  the maximum ap of the woolie
     */
    public int getMaxAtk() {
        return maxAtk;
    }

    /**
     * Accessor Accesses the delay between each attack of this woolie
     * @return the delay between each attack of this woolie
     */
    public int getHitTime() {
        return hitTime;
    }

    /**
     * Accessor for the maximum health value of this woolie
     * @return the max hp for this woolie
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Woolie constructor
     * @param name Whats in a name?
     * @param minAtk Minimum Attack power
     * @param maxAtk Maximum Attack Power
     * @param hitTime Delay between hits (What is this, Final Fantasy?)
     * @param maxHp Max hit points
     */

    public Woolie(String name, int minAtk, int maxAtk, int hitTime, int maxHp){
        //Constructor constructs things
        this.name = name;
        this.minAtk = minAtk;
        this.maxAtk = maxAtk;
        this.hitTime = hitTime;
        this.maxHP = maxHp;
        HP = maxHp;
    }

    /**
     * Accessor for the current amount of health the Woolie has
     * @return current hp of the woolie
     */
    public int getCurrentHP() {
        return HP;
    }

    /**
     * Secondary constructor for Woolies. This one is used assumably when reading from a filetype
     * @param params the parameters for this woolie
     */
    public Woolie(String[] params){
        name = params[0];
        minAtk = Integer.parseInt(params[1]);
        maxAtk = Integer.parseInt(params[2]);
        hitTime = Integer.parseInt(params[3]);
        maxHP = Integer.parseInt(params[4]);
        HP = maxHP;
    }

    /**
     * Returns a random attack value between the minimum and maximum attack values of this woolie
     * @return whatever attack value ths woolie is attacking with
     */
    public int getAttackAmount(){
        Random atkVal = new Random();
        atkVal.setSeed(seed);
        return atkVal.nextInt((maxAtk - minAtk) + 1 ) + minAtk;
    }

    /**
     * Reduces the Woolie's health by the specified amount
     * @param dmg the amount to reduce the woolie's health by
     */
    public void takeDamage(int dmg){
        HP -= dmg;
    }

    /**
     * Check if the woolie's current HP is above zero
     * @return True if current HP is greater than zero, false otherwise
     */
    public boolean isOK(){
        return HP > 0;
    }

    /**
     * Reset the Woolie's state.
     */
    public void reset(){
        HP = maxHP;
    }

    /**
     * ToString to represetn a Woolie
     * @return a string representation of a woolie
     */
    public String toString(){
        return getName() + ": Max HP " + getMaxHP() + ", Min Attack " + getMinAtk() + ", Max Attack " + getMaxAtk() +
                ", Hit Time " + getHitTime();
    }
}
