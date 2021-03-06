//@ThreadSafe
public class ThreadGate {
	// 条件谓词：opened-since(n) (isOpen || generation>n)
	//@GuardedBy("this")
	private boolean isOpen;
	//@GuardedBy("this")
	private int generation;

	public synchronized void close() {
		isOpen = false;
	}

	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	// 阻塞并直到：opened-since(generation on entry)
	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation) {
			wait();
		}
	}
}