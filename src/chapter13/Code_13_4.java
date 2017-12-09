public boolean trySendOnSharedLine(String message, long timeout, TimeUnit unit)
	throws InterruptedException {

	long nanosTolock = unit.toNanos(timeout) - estimatedNanosToSend(message);
	if (!lock.tryLock(nanosTolock, NANOSECONDS)) {
		return false;
	}
	try {
		return sendOnSharedLine(message);
	} finally {
		lock.unlock();
	}
}