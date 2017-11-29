public class IndexingService {
	private static final File POISON = new File("");
	private final IndexerThread consumer = new IndexerThread();
	private final CrawlerThread producer = new CrawlerThread();
	private final BlockingQueue<File> queue;
	private final FileFilter fileFilter;
	private final Final root;

	class CrawerThread extends Thread {
		public void run() {
			try {
				crawl(root);
			} catch (InterruptedException e) {
				/* 放生异常*/
			} finally {
				while (true) {
					try {
						queue.put(POINSON);
						break;
					} catch (InterruptedException e1) {
						/* 重新尝试 */
					}
				}
			}
		}

		private void crawl(File root) throws InterruptedException {
			// ...
		}
	}

	class IndexerThread extends Thread {
		public void run() {
			try {
				while (true) {
					File file = queue.take();
					if (file == POINSON) {
						break;
					} else {
						indexFile(file);
					}
				}
			} catch (InterruptedException consumed) {
				
			}
		}
	}

	public void start() {
		producer.start();
		consumer.start();
	}

	public void stop() {
		producer.interrupt();
	}

	public void awaitTermination() throws InterruptedException {
		consumer.join();
	}
}