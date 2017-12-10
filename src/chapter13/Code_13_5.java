public boolean sendOnSharedLind(String message)
	throws InterrutedException {

	lock.lockInterruptibly();
	try {
		return cancellableSendOnSharedLine(message);
	} finally {
		lock.unlock();
	}
}

private boolean cancellableSendOnSharedLine(String message) throws InterruptedException {
	//...
}