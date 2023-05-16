/**
 * @author Yoav Shragay <\yoavshra@gmail.com> 318730066s
 */
public class Counter {
    private int starValue;

    /**
     * Instantiates a new Counter.
     *
     * @param starValue the star value
     */
    public Counter(int starValue) {
        this.starValue = starValue;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    void increase(int number) {
        starValue = starValue + number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        starValue = starValue - number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    int getValue() {
        return starValue;
    }


    /**
     * Update.
     *
     * @param newVal the new val
     */
    public void update(int newVal) {
        this.starValue = newVal;
    }
}
