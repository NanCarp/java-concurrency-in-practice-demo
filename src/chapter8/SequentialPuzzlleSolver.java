public class SequentialPuzzleSolver<P, M> {
	private final Puzzle<P, M> puzzle;
	private final Set<P> seen = new HashSet<P>();

	public SequentialPuzzleSolver(Puzzle<p, M> puzzle) {
		this.puzzle = puzzle;
	}

	public List<M> solve() {
		P pos = puzzle.initialPosition();
		return search(new Node<P, M>(pos, null, null));
	}

	private List<M> search(Node<P, M> node) {
		if (!seen.contains(node.pos)) {
			seen.add(node.pos);
			if (puzzle.isGoal(node.pos)) {
				return node.asMoveList();
			}
			for (M move : puzzle.legalMoves(node.pos)) {
				P pos = puzzle.move(node.pos, move);
				Node<P, M> child = new Node<P, M>(pos, move, node);
				List<m> result = search(child);
				if (result != null) {
					return result;
				}
			}
		}
		reuturn null;
	}

	static class Node<P, M> {
		
	}
}