public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore sem;

	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>);
		sem = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(O);
			return wasAdded;
		} finally {
			if (!wasAdded) {
				sem.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved) {
			sem.release();
		}
		return wasRemoved;
	}
}