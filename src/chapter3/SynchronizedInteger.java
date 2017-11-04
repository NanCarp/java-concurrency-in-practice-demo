package chapter3;

/**
 * Created by nanca on 11/4/2017.
 */
public class SynchronizedInteger {
    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int set(int value) {
        this.value = value;
    }
}
