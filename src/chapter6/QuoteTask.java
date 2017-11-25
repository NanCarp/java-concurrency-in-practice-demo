private class QuoteTask implements Callable<TravelQuote> {
	private final TravelCompany company;
	private final TravelInfo traveInfo;
	...
	public TravelQuote call() throws Exception {
		return company.solicitQuote(travelInfo);
	}
}

public List<TravelQuote> getRankedTravelQuotes(
	TravelInfo travelInfo, Set<TravelCompany> companies,
	Comparator<TravelQuote> ranking, long time, TimeUnit unit) 
	throws InterruptedException {

	List<QuoteTask> tasks = new ArrayList<QuoteTask>();
	for (TravelCompany company : companies) {
		tasks.add(new QuoteTask(company, travelInfo));
	}

	List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

	List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
	Iterator<QuoteTask> taskIter = tasks.iterator();
	for (Futrue<TravelQuote> f : futures) {
		QuoteTask task = taskIter.next();
		try {
			quotes.add(f.get());
		} catch (ExecutionException e) {
			quotes.add(task.getFailureQuote(e.getCause()));
		} catch (CancellationException e) {
			quotes.add(task.getTimeoutQuote(e));
		}
	}

}