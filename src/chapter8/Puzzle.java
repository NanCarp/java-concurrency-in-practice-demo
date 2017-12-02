public interface Puzzel<P, M> {
	P initialPosition();
	boolean isGoal(P position);
	Set<M> legalMoves(P position);
	P move(P postion, M move);
}