class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			BigInteger p = BigInteger.ONE:
			while (!cancelled) {
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException consumed) {}
	}

	public void cancel() {
		canceled = true;
	}
}

void consumePrimes() throws InterruptedException {
	BlockingQueue<BigInteger> primes = ...;
	BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
	producer.start();
	try {
		while (needMorePrimes()) {
			consume(primes.take()):
		}
	} finally {
		producer.cancel();
	}
}