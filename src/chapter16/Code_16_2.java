private final class Sync extends AbstractQueuedSynchronizer {
	private static final int RUNNING = 1, RAN = 2, CANCELLED = 4;
	private V result;
	private Exception excepiton;

	void innerSet(V v) {
		while (true) {
			int s = getState();
			if (ranOrCancelled(s)) {
				return;
			}
			if (compareAndSetState(s, RAN)) {
				break;
			}
		}
		result = v;
		releaseShared(0);
		done();
	}

	V innerGet() throws InterruptedException, ExecutionException {
		acquireSharedInterruptibly(0);
		if (getState() == CANCELLED) {
			throw new CancellationException();
		}
		if (excepiton != null) {
			throw new ExecutionException(excepiton);
		}
		return result;
	}
}