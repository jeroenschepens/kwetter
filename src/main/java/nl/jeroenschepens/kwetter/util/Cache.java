package nl.jeroenschepens.kwetter.util;

public abstract class Cache<T> {

	private final int refreshRate;

	private long lastRefresh = 0;

	private T value;

	protected abstract T storeValue();

	public Cache() {
		refreshRate = 10;
	}

	public Cache(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	public T getValue() {
		if (value != null) {
			value = storeValue();
			postponeRefresh();
		} else {
			if (refresh()) {
				value = storeValue();
			}
		}
		return value;
	}

	private boolean refresh() {
		long time = System.currentTimeMillis();
		if (time > lastRefresh + refreshRate) {
			lastRefresh = time;
			return true;
		}
		return false;
	}

	public void forceRefresh() {
		lastRefresh = 0;
	}

	public void postponeRefresh() {
		lastRefresh = System.currentTimeMillis();
	}
}