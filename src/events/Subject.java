package events;
import java.util.Collection;
import java.util.HashSet;

/**
 * Defines the notifyObservers and a set of Observers
 */
public abstract class Subject {
	private Collection<Observable> observers = new HashSet<>();

	/**
	 * Adds an observer to the collections of Observables
	 * @param observer
	 */
	public void addObserver(Observable observer){
		this.observers.add(observer);
	}

	/**
	 * Removes an observer from the observables
	 * @param observer
	 */
	public void removeObserver(Observable observer){
		this.observers.remove(observer);
	}

	/**
	 * Remove all observables from the subject
	 */
	public void removeAllObservers(){
		this.observers = new HashSet<>();
	}

	/**
	 * Notifies all the observers of an event
	 * @param event
	 */
	public void notifyObservers(Event event){
		for (Observable observer : this.observers){
			if (observer == null)continue;
			observer.update(event);
		}
	}
}
