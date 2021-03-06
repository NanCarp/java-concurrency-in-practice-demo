public class UEHLogger implements Thread, UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonumousLogger();
		logger.log(Level.SEVERE,
			       "Thread terminated with exception: " + t.getName(),
			       e);
	}
}