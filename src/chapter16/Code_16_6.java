//延长初始化占位类模式
//@ThreadSafe
public calss ResourceFactory {
	private static class ResourceHolder {
		public static Resource resource = new Resource();
	}

	public static Resource getResource() {
		return ResourceHolder.resource;
	}
}