public class Preloader {
	private final FutureTask<ProductInfo> future =
		new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
			public ProductInfo call() throws DataLoadException {
				return loadProductInfo();
			}
		});

	private final Tread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws DataLoadException, InterrutpedException {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException) {
				throw (DataLoadException) cause;
			} else {
				throw launderThrowable(cause);
			}
		}
	}

	public static RunntimeException launderThrowable(Throwable t) {
		if (t instanceof RunntimeException) {
			return (RunntimeException) t;
		} else if (t instanceof Error) {
			throw (Error) t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
}