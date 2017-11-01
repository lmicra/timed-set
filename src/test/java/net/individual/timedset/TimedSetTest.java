package net.individual.timedset;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class TimedSetTest
{

	private static class AClass implements TimedElement<AClass>
	{

		private final Date lastUpdated;
		private final int id;
		private final String description;

		public AClass( Date lastUpdated,
					   int id,
					   String description )
		{
			this.lastUpdated = lastUpdated != null ? lastUpdated : new Date();
			this.id = id;
			this.description = description != null ? description.trim() : "";
		}

		@Override
		public String toString()
		{
			return String.format( "{%d, \"%s\"}", this.id, this.description );
		}

		@Override
		public boolean isSameElementAs( AClass that )
		{
			return this.id == that.id;
		}

		@Override
		public int compareTo( AClass that )
		{
			if ( this.id != that.id )
			{
				return this.id - that.id;
			}
			return that.lastUpdated.compareTo( this.lastUpdated );
		}
	}

	@Test
	public void testAdd()
	{
		TimedSet<AClass> set = new TimedSet<>();
		Assert.assertEquals( "[]", String.valueOf( set ));

		set.add( new AClass(new Date(), 1, "1" ) );
		set.add( new AClass(new Date(), 2, "2" ) );
		Assert.assertEquals( "[{1, \"1\"}, {2, \"2\"}]", String.valueOf( set ));

		set.clear();
		set.add( new AClass(new Date(), 1, "1" ) );
		set.add( new AClass(new Date(), 1, "2" ) );
		Assert.assertEquals( "[{1, \"2\"}]", String.valueOf( set ));

		set.clear();
		set.add( new AClass(new Date(2017,1,1), 1, "1" ) );
		set.add( new AClass(new Date(2017,0,1), 1, "2" ) );
		Assert.assertEquals( "[{1, \"1\"}]", String.valueOf( set ));

		set.clear();
		set.add( new AClass(new Date(2017,1,1), 1, "1" ) );
		set.add( new AClass(new Date(2017,0,1), 2, "2" ) );
		Assert.assertEquals( "[{1, \"1\"}, {2, \"2\"}]", String.valueOf( set ));
	}
}
