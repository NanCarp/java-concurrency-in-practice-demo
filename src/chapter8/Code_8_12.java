public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
	ExecutorService exec = Executors.newCachedThreadPool();
	Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
	parallelRecursive(exec, nodes, resultQueue);
	exec.shutdown();
	exec.awaiTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	return resultQueue;
}