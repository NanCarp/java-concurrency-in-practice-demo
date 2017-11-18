public static void startIndexing(File roots) {
	BlockingQueue<File> queue = new LinkedBlocingQueue<File>(BOUND);
	FileFilter filter = new FileFilter() {
		publilc boolean accept(File file) {
			return true;
		}
	}

	for (File root : roots) {
		new Thread(new FileCrawler(queue, filter, root)).start();
	}

	for (int i = 0; i < N_CONSUMERS; i++) {
		new Thread(new Indexer(queue)).start();
	}
}