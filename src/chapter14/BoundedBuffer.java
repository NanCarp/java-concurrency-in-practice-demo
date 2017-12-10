//@ThreadSafe
public class BoundedBuffer<V> extends BaseBundedBuffer<V> {
	// 条件谓词： not-full (!isFull())
	// 条件谓词： not-empty (!isEmpty())

	public BoundedBuffer(int size) {
		super(size);
	}

	// 阻塞并直到： not-full
	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			wait();
		}
		doPut(v);
		notifyAll();
	}

	// 阻塞并直到： not-empty
	public synchronized V take() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
		V v = doTake();
		notifyAll();
		return v;
	}
}