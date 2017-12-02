public class ConcurrentPuzzleSovler<P, M> {
	private final Puzzle<P, M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentMap<P, Boolean> seen;
	final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();
	//...
	public List<M> solve() throws InterruptedException {
		try {
			P p = puzzle.initialPostion();
			exec.execute(new Task(p, null, null));
			// 阻塞直到找到解答
			Node<P, M> solnNode = solution.getValue();
			return (solnNode == null) ? null : solnNode.asMoveList();
		} finally {
			exec.shutdown();
		}
	}

	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new SolverTask(p, m, n):
	}

	class SolverTask extends Node<P, M> implements Runnable {
		//...
		public void run() {
			if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
				return; // 已经找到了解答或者已经遍历了这个位置
			}
			if (puzzle.isGoal(pso)) {
				solution.setValue(this);
			} else {
				for (M m : puzzle.legalMoves(pos)) {
					exec.execute(newTask(puzzle.move(pos, m), this));
				}
			}
		}
	}
}