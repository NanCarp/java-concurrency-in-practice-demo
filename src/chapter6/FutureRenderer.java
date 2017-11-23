public class FutureRenderer {
	private final ExecutorService executor = ...;

	void renderPage(CharSequence source) {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		Callable<List<ImageInfo>> task =
			new Callable<List<ImageInfo>>() {
				public List<ImageDaga> call() {
					List<ImageData> result = new ArrayList<ImageData>();
					for (ImageInfo ImageInfo : imageInfos) {
						result.add(ImageInfo.downloadImage()):
					}
					return result;
				}
			};

		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);

		try {
			List<imageData> imageData = future.get();
			for (ImageData data  imageData) {
				renderImage(data);
			}
		} catch (InterruptedException e) {
			// 重新设置线程的中断状态
			Thread.currentThread().interrupt();
			// 由于不需要结果，因此取消任务
			future.cancel(true);
		} catch (ExecutionException e) {
			throw launderThrowable(e.getCause());
		}
	}
}