package main;
import java.util.ArrayList;
import java.util.List;

import events.Event;

public abstract class Subject {
	private List<Observerable> observers = new ArrayList<>();
	
	public void addObservers(Observerable observer){
		this.observers.add(observer);
	}
	
	public void notify(Event event){
		for (Observerable observer : this.observers){
			observer.update(event);
		}
	}
}
