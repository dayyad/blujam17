package events;

public interface Observable {
	/**
	 * Updates the Observer called only from a subject
	 * @param event The event that happened from the Subject
	 */
	void update(Event event);
}
