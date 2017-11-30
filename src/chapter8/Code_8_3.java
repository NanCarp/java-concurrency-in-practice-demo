ThreadPoolExecutor executor 
	= new ThreadPoolExecutor(N_THREADS, N_THREADS,
		0L, TIMEUNIT.MILLISECONDS,
		new LinkedBlockingQueue<Runnable>(CAPACITY));
executor.setRejectedExecutorHanlter(new ThreadPoolExecutor.CallerRunsPolicy());