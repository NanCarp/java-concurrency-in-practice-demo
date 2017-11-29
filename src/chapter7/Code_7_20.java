boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) 
	throws InterruptedExcepiton {

	ExecutorService exec = Executors.newCachedThreadPool();
	final AtomicBoolean hasNewMail = new AtomicBoolean(false);

	try {
		for (final String host : hosts) {
			exec.execute(new Runnable() {
				public void run() {
					if (checkMail(host)) {
						hasNewMail.set(true);
					}
				}
			});
		}
	} finally {
		exec.shutsown();
		exec.awaitTermination(timeout, unit);
	}
	return hasNewMail.get();
}