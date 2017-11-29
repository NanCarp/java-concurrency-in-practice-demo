public class LogService {
	private final ExecutorService exec = newSingleThreadExecutor();
	...
	public void start() {}

	public void stop() throws InterruptedException {
		try {
			exec.shutdown();
			exec.awaitTermination(TIMEOUT, UNIT);
		} finally {
			writer.close();
		}
	}

	public void log(String msg) {
		exec.excute(new WriteTask(msg));
	} catch (RejectedExecutionException ignored) {
		
	}
}