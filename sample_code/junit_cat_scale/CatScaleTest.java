import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import org.mockito.*;
import static org.mockito.Mockito.*;

public class CatScaleTest {
	CatScale scale;
	Cat cat;
	
	@Before
	public void setUp() {
		scale = new CatScale();
		cat = mock(Cat.class);
	}

	
	/**
	 * Test case for String report(Cat cat).
	 * Preconditions: A new CatScale is created and assigned to "scale".
	 *                A new Cat weighing 80 pounds is created and assigned to "cat".
	 * Execution steps: Call scale.report(cat).
	 * Postconditions: Return value is "Overweight".
	 *                 cat.getWeight() has been called.
	 */
	@Test
	public void testReport1() {
		when(cat.getWeight()).thenReturn(80);
		assertEquals("Overweight over 80", "Overweight", scale.report(cat));
		verify(cat).getWeight();
		
	}
	
	@Test
	public void testReport2() {
		when(cat.getWeight()).thenReturn(5);
		assertEquals("Underweight under 10", "Underweight", scale.report(cat));
		verify(cat, times(2)).getWeight();
	}
	
	@Test
	public void testReport3() {
		when(cat.getWeight()).thenReturn(30);
		assertEquals("Normal between 10 and 40", "Normal", scale.report(cat));
		verify(cat, times(2)).getWeight();
	}
}
