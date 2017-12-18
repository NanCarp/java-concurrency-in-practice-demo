public class CasNumberRange {
	//@Immutable
	private static class IntPair {
		final int lower; // 不变性条件：lower <= upper
		final int upper;
		//...
	}

	private final AtomicReference<IntPair> values = 
		new AtomicReference<IntPair>(new IntPair(0, 0));

	public int getLower() {
		return values.get().lower;
	}

	public getUpper() {
		return values.get().upper;
	}

	public void setLower(int i){
		while (true) {
			IntPair oldv = values.get();
			if (i > oldv.upper) {
				throw new IllegalArgumentException(
					"Can't set lower to " + i + " > upper");
			}
			IntPair newv = new IntPair(i, oldv.upper);
			if (values.compareAndSet(oldv, newv)) {
				return;
			}
		}
	}
}