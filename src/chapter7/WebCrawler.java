public abstract class WebCrawler {
	private volatile TrackingExecutor exec;
	@GuardedBy("this")
	private final Set<URL> urlsToCrawl = new HashSet<URL>();
	//...
	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for (URL url : urlsToCrawl) {
			submitCrawTask(url);
		}
		urlsToCrawl.clear();
	}

	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(TIMEOUT, UNIT)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	protected abstract List<URL> processPage(URL url);

	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable task : uncrewled) {
			urlsToCrawl.add(((CrawlTask) task).getPage());
		}
	}

	private void submitCrawTask(URL u) {
		exec.execute(new CrawlTask(u));
	}

	private class CrawTask implements Runnable {
		private final URL url;
		//...
		public void run() {
			for (URL link : processPage(url)) {
				if (Thread.currentThread().isInterrupted()) {
					return;
				}
				submitCrawTask(link);
			}
		}
		public URL getPage() {
			return url;
		}
	}
}