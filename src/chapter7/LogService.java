public class LogService {
	private final BlockingQueue<String> queue;
	private final LoggerThread LoggerThread;
	private final PrintWriter writer;
	@GuardedBy("this") private boolean isShutdown;
	@GuardedBy("this") private int reservations;

	public void start() {
		LoggerThread.start();
	}

	public void stop() {
		synchronized (this) {
			isShutdown = true;
		}
		loggerThread.interrupt();
	}

	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutdown) {
				throw new IllegalStateException("...");
			}
			++reservations;
		}
		queue.put(msg);
	}

	private class LoggerThread extends Thread {
		public void run() {
			try {
				while (true) {
					try {
						synchronized (LogService.this) {
							if (isShutdown && reservations == 0) {
								break;
							}
							String msg = queue.take();
							synchronized (LogService.this) {
								--reservations;
							}
						}
					} catch (InterruptedException e) {
						/* retry */
					} 
				}
			} finally {
				writer.close();
			}
			
		}
	}
}