package chapter3;

/**
 * Created by nanca on 11/4/2017.
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
