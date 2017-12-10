//@ThreadSafe
public class SleepyBundedBuffer<V> extends BaseBoundedBuffer<V> {
	public SleepyBundedBuffer(int size) {
		super(size);
	}

	public void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!ifFull()) {
					doPut(v);
					return;
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

	public V take() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isEmpty()) {
					return doTake();
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}
}