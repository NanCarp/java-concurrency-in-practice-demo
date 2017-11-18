public class TaskRunnable implements Runnable {
	BlockingQueue<Task> queue;
	...
	public void run() {
		try {
			processTask(queue.take());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}