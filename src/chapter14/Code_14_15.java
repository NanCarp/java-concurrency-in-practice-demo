protected boolean tryAcquire(int ignored) {
	final Thread current = Thread.currentThread();
	int c = getState();
	if (c == 0) {
		if  (compareAndSetState(0, 1)) {
			owner = current;
			return true;
		}
	} else if (current == owner) {
		setState(c+1);
		return true;
	}
	return false;
}