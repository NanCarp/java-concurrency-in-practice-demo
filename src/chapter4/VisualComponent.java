public class VisualComponent {
	private final List<KeyListener> keyListeners = 
		new ConpyOnWriteArrayList<KeyListener>();
	private final List<MouseListener> mouseListeners =
		new ConpyOnWriteArrayList<MouseListener>();

	public void addKeyListener(KeyListener listener) {
		keyListeners.add(listener);
	}

	public void addMouseListener(MouseListener listener) {
		mouseListeners.add(listener);
	}

	public void removeKeyListener(KeyListener listener) {
		keyListeners.remove(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		mouseListeners.remove(listener);
	}

}