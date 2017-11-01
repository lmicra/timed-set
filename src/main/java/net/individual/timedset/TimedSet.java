package net.individual.timedset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TimedSet<T extends TimedElement<T>> extends ArrayList<T>
{

	/**
	 * ignored in this implementation, delegates to {@link TimedSet#add(net.individual.timedset.TimedElement)}
	 *
	 * @param index
	 * @param element
	 */
	@Override
	public void add( int index,
					 T element )
	{
		this.add( element );
	}

	/**
	 * attempts to add this element to the list,
	 * but only if this element does not already exist
	 * or it's a more recent version of this element
	 *
	 * @param e element to be appended to this list
	 *
	 * @return true (as specified by {@link Collection#add})
	 */
	@Override
	public boolean add( T e )
	{
		if ( super.isEmpty() )
		{
			super.add( e );
			return true;
		}

		int insertionPoint = Collections.binarySearch( this, e );

		// replace the existing elem if it is the exact same copy
		if ( insertionPoint > -1 )
		{
			super.set( insertionPoint, e );
			return true;
		}

		insertionPoint = -insertionPoint - 1;

		// don't add if the new elem is not more recent that the existing one
		if ( ( insertionPoint > 0 ) && e.isSameElementAs( super.get( insertionPoint - 1 ) ) )
		{
			return false;
		}

		// replace the existing elem if the new elem is more recent
		if ( ( insertionPoint < super.size() ) && e.isSameElementAs( super.get( insertionPoint ) ) )
		{
			super.set( insertionPoint, e );
			return true;
		}

		super.add( insertionPoint, e );
		return true;
	}
}
