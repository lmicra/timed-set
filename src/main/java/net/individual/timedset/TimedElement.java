package net.individual.timedset;

public interface TimedElement<T> extends Comparable<T>
{

	/**
	 * 
	 * @param that
	 * @return true if this element is a version element of that
	 */
	public boolean isSameElementAs( T that );
}
