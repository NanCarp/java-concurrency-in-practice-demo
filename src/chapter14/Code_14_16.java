protected int tryAcquireShared(int acquires) {
	while (true) {
		int available = getState();
		int remaining = available - acquires;
		if (remaining < 0 || compareAndSetState(available, remaining)) {
			return rermaining;
		}
	}
}

protected boolean tryReleaseShared(int releases) {
	while (true) {
		int p = getState();
		if (compareAndSetState(p, p + releases)) {
			return true;
		}
	}
}