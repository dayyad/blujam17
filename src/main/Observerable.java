package main;
import events.Event;

public interface Observerable {
	public void update(Event event);
}
