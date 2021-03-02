import static org.junit.Assert.assertEquals;

import gov.nasa.jpf.vm.Verify;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>Uses the Java Path Finder model checking tool to check BeanCounterLogic in
 * various modes of operation. It checks BeanCounterLogic in both "luck" and
 * "skill" modes for various numbers of slots and beans. It also goes down all
 * the possible random path taken by the beans during operation.
 */

public class BeanCounterLogicTest {
	private static BeanCounterLogic logic; // The core logic of the program
	private static Bean[] beans; // The beans in the machine
	private static String failString; // A descriptive fail string for assertions

	private static int slotCount; // The number of slots in the machine we want to test
	private static int beanCount; // The number of beans in the machine we want to test
	private static boolean isLuck; // Whether the machine we want to test is in "luck" or "skill" mode

	/**
	 * Sets up the test fixture.
	 */
	@BeforeClass
	public static void setUp() {
		if (Config.getTestType() == TestType.JUNIT) {
			slotCount = 5;
			beanCount = 3;
			isLuck = true;
		} else if (Config.getTestType() == TestType.JPF_ON_JUNIT) {
			slotCount = Verify.getInt(1, 5);
			beanCount = Verify.getInt(0, 3);
			isLuck = Verify.getBoolean();
			/*
			 * TODO: Use the Java Path Finder Verify API to generate choices for slotCount,
			 * beanCount, and isLuck: slotCount should take values 1-5, beanCount should
			 * take values 0-3, and isLucky should be either true or false. For reference on
			 * how to use the Verify API, look at:
			 * https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF
			 */
		} else {
			assert (false);
		}

		// Create the internal logic
		logic = BeanCounterLogic.createInstance(slotCount);
		// Create the beans
		beans = new Bean[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = Bean.createInstance(slotCount, isLuck, new Random(42));
		}

		// A failstring useful to pass to assertions to get a more descriptive error.
		failString = "Failure in (slotCount=" + slotCount
				+ ", beanCount=" + beanCount + ", isLucky=" + isLuck + "):";
	}

	@AfterClass
	public static void tearDown() {
	}

	/**
	 * Test case for void void reset(Bean[] beans).
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 * Invariants: If beanCount is greater than 0,
	 *             	remaining bean count is beanCount - 1
	 *             	in-flight bean count is 1 (the bean initially at the top)
	 *             	in-slot bean count is 0.
	 *             If beanCount is 0,
	 *             	remaining bean count is 0
	 *             	in-flight bean count is 0
	 *             	in-slot bean count is 0.
	 */
	@Test
	public void testReset() {
		logic.reset(beans);
		
		if (beanCount > 0) {
			assertEquals(logic.getRemainingBeanCount(), beanCount - 1);
			int ctSlot = 0;
			int inFlight = 0;
			for (int i = 0; i < slotCount; i++) {
				int slot = logic.getSlotBeanCount(i);
				ctSlot += slot;
				if (i != slotCount) {
					int flight = logic.getInFlightBeanXPos(i);
					inFlight = flight == -1 ? inFlight : inFlight + 1;
				}
			}
			assertEquals(inFlight, 1);
			assertEquals(ctSlot, 0);
		} else if (beanCount == 0) {
			assertEquals(logic.getRemainingBeanCount(), 0);
			int ctSlot = 0;
			int inFlight = 0;
			for (int i = 0; i < slotCount; i++) {
				int slot = logic.getSlotBeanCount(i);
				ctSlot += slot;
				if (i != slotCount) {
					int flight = logic.getInFlightBeanXPos(i);
					inFlight = flight == -1 ? inFlight : inFlight + 1;
				}
			}
			assertEquals(inFlight, 0);
			assertEquals(ctSlot, 0);			
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             	all positions of in-flight beans are legal positions in the logical coordinate system.
	 */
	@Test
	public void testAdvanceStepCoordinates() {
		logic.reset(beans);
		
		boolean working = true;
		while (logic.advanceStep() && working) {
			for (int i = 0; i < slotCount; i++) {
				int xpos = logic.getInFlightBeanXPos(i);
				if (!(xpos >= -1 && xpos < slotCount)) {
					working = false;
				}
			}
		}
		assertEquals(working, true);
		
		// TODO: Implement
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             	the sum of remaining, in-flight, and in-slot beans is equal to beanCount.
	 */
	@Test
	public void testAdvanceStepBeanCount() {
		logic.reset(beans);
		
		while (logic.advanceStep()) {
			int ctSlot = 0;
			int inFlight = 0;
			for (int i = 0; i < slotCount; i++) {
				int slot = logic.getSlotBeanCount(i);
				ctSlot += slot;
				int flight = logic.getInFlightBeanXPos(i);
				inFlight = flight == -1 ? inFlight : inFlight + 1;
			}
			int total = logic.getRemainingBeanCount() + ctSlot + inFlight;
			assertEquals(total, beanCount);
		}
		
		// TODO: Implement
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After the machine terminates,
	 *             	remaining bean count is 0
	 *             	in-flight bean count is 0
	 *             	in-slot bean count is beanCount.
	 */
	@Test
	public void testAdvanceStepPostCondition() {
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		
		int remaining = logic.getRemainingBeanCount();
		int ctSlot = 0;
		int inFlight = 0;
		for (int i = 0; i < slotCount; i++) {
			int slot = logic.getSlotBeanCount(i);
			ctSlot += slot;
			int flight = logic.getInFlightBeanXPos(i);
			inFlight = flight == -1 ? inFlight : inFlight + 1;
		}
		assertEquals(remaining, 0);
		assertEquals(inFlight, 0);
		assertEquals(ctSlot, beanCount);
		// TODO: Implement
	}
	
	/**
	 * Test case for void lowerHalf()().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.lowerHalf().
	 * Invariants: After calling logic.lowerHalf(),
	 *             	slots in the machine contain only the lower half of the original beans.
	 *             		Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             	Check each slot for the expected number of beans after having called logic.lowerHalf().
	 */
	@Test
	public void testLowerHalf() {
		logic.reset(beans);
		while (logic.advanceStep()) {
		}

		int[] slotCounts = new int[slotCount + 1];
		for (int s = 0; s < slotCount; s++) {
			slotCounts[s] = logic.getSlotBeanCount(s);
		}
		
		int toRemove = beanCount % 2 == 0 ? beanCount / 2 : (beanCount - 1) / 2;
		for (int z = slotCount; z >= 0 && toRemove > 0; z--) {
			if (toRemove >= slotCounts[z]) {
				toRemove -= slotCounts[z];
				slotCounts[z] = 0;
			} else {
				slotCounts[z] -= toRemove;
				toRemove = 0;
			}
		}
		
		logic.lowerHalf();
		
		int ctSlot = 0;
		for (int i = 0; i < slotCount; i++) {
			int slot = logic.getSlotBeanCount(i);
			assertEquals(slotCounts[i], slot);
			ctSlot += slot;
		}
		int check = beanCount % 2 == 0 ? beanCount / 2 : (beanCount + 1) / 2;
		assertEquals(ctSlot, check);
		// TODO: Implement
	}
	
	/**
	 * Test case for void upperHalf().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.upperHalf().
	 * Invariants: After calling logic.upperHalf(),
	 *             	slots in the machine contain only the upper half of the original beans.
	 *             		Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             	Check each slot for the expected number of beans after having called logic.upperHalf().
	 */
	@Test
	public void testUpperHalf() {
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		
		int[] slotCounts = new int[slotCount + 1];
		for (int s = 0; s < slotCount; s++) {
			slotCounts[s] = logic.getSlotBeanCount(s);
		}

		int toRemove = beanCount % 2 == 0 ? beanCount / 2 : (beanCount - 1) / 2;
		for (int z = 0; z < slotCount && toRemove > 0; z++) {
			if (toRemove >= slotCounts[z]) {
				toRemove -= slotCounts[z];
				slotCounts[z] = 0;
			} else {
				slotCounts[z] -= toRemove;
				toRemove = 0;
			}
		}
		
		logic.upperHalf();
		
		int ctSlot = 0;
		for (int i = 0; i < slotCount; i++) {
			int slot = logic.getSlotBeanCount(i);
			assertEquals(slotCounts[i], slot);
			ctSlot += slot;
		}
		int check = beanCount % 2 == 0 ? beanCount / 2 : (beanCount + 1) / 2;
		assertEquals(ctSlot, check);
		// TODO: Implement
	}
	
	/**
	 * Test case for void repeat().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.repeat();
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: If the machine is operating in skill mode,
	 *             	bean count in each slot is identical after the first run and second run of the machine. 
	 */
	@Test
	public void testRepeat() {
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		
		int[] slotCounts = new int[slotCount];
		for (int i = 0; i < slotCount; i++) {
			slotCounts[i] = logic.getSlotBeanCount(i);
		}
		
		logic.repeat();
		while (logic.advanceStep()) {
		}
		
		if (!isLuck) {
			for (int z = 0; z < slotCount; z++) {
				assertEquals(slotCounts[z], logic.getSlotBeanCount(z));
			}
		}
		// TODO: Implement
	}
	
	/**
	 * Test case for void getAverageSlotBeanCount().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.repeat();
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: If the machine is operating in skill mode,
	 *             	averageSlotBeanCount is identical after the first run and second run of hte machine. 
	 */
	@Test
	public void testAverage() {
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		
		double firstAvg = logic.getAverageSlotBeanCount();
		
		logic.repeat();
		while (logic.advanceStep()) {
		}
		
		double secondAvg = logic.getAverageSlotBeanCount();
		
		if (!isLuck) {			
			assertEquals(firstAvg, secondAvg, 0.0);
		}
		
	}
}
