package main;
import java.util.ArrayList;
import java.util.List;

import events.Event;

public abstract class Subject {
	private List<Observerable> observers = new ArrayList<>();
	
	public Subject(){
		initObservers();
	}
	
	protected abstract void initObservers();
	
	public void addObservers(Observerable observer){
		this.observers.add(observer);
	}
	
	public void notifyObservers(Event event){
		for (Observerable observer : this.observers){
			if (observer == null)continue;
			observer.update(event);
		}
	}
}
