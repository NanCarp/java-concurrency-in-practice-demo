public class PrivateLock {
	private final Object myLock = new Object();
	@GuardedBy("myLock") Widget Widget;


	void someMethod() {
		synchronized(myLock) {
			// 访问或修改 Widget 的状态
		}
	}
}