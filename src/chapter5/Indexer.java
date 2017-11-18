public class Indexer implements Runnable {
	private final BlockingQueue<File> queue;

	public Indexer(BlockingQueue<File> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while(true) {
				indexer(queue.take());
			}
		} catch (InterrutedException e) {
			Thread.cuurrentThread().interrupt();
		}
	}
}