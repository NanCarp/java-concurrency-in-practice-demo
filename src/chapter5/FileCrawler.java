public class FileCrawller implements Runnable {
	private final BlockingQueue<File》 fileQueue;
	private final FileFilter FileFilter;
	private final File root;
	...
	public void run() {
		crawl(root);
	}

	private void crawl(File root) throws InterrutedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory()) {
					crawl(entry);
				} else if (!alreadyIndexed(entry)) {
					fileQueue.put(entry);
				}
 			}
		}
	}
}

