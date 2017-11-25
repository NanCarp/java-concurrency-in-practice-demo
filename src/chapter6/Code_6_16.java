Page renderPageWithad() throws InterruptedException {
	long endNanos = System.nanoTime() + TIME_BUDGET;
	Future<Ad> f = exec.submit(new FetchAdTask());
	// 等待广告的同时显示页面
	Page page = renderPageBody();
	Ad ad;
	try {
		// 只等待指定的时间长度
		long timeLeft = endNanos - System.nanoTime();
		ad = f.get(timeLeft, NANOSECONDS);
	} catch (ExecutionException e) {
		ad = DEFAULT_AD;
	} catch (TimeoutException e) {
		ad = DEFAULT_AD;
		f.cancel(true);
	}
	page.setAd(ad);
	return page;
}