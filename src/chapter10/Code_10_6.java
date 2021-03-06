//@ThreadSafe
class Taxi {
	//@GuardedBy("this")
	private Point location, destination;
	private final Dispatcher dispather;
	//...
	public synchronized Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		boolean reachedDestination;
		synchronized (this) {
			this.location = location;
			reachedDestination = location.equals(destination);
		}
		if (reachedDestination) {
			dispather.notifyAvailable(this);
		}
	}
}

// @ThreadSafe
class Dispatcher {
	//@GuardedBy("this")
	private final Set<Taxi> taxis;
	//@GuardedBy("this")
	private final Set<Taxi> availableTaxis;
	// ...
	public synchronized void notifyAvailable(Taxi taxi) {
		availableTaxis.add(taxi);
	}

	public Image getImage() {
		Set<Taxi> copy;
		synchronized (this) {
			copy = new HashSet<Taxi>(taxis);
		}
		Image image = new Image();
		for (Taxi t : copy) {
			image.drawMarker(t.getLocation());
		}
		return image;
	}
}