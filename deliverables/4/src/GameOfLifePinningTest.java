import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;



public class GameOfLifePinningTest {
	/*
	 * READ ME: You may need to write pinning tests for methods from multiple
	 * classes, if you decide to refactor methods from multiple classes.
	 * 
	 * In general, a pinning test doesn't necessarily have to be a unit test; it can
	 * be an end-to-end test that spans multiple classes that you slap on quickly
	 * for the purposes of refactoring. The end-to-end pinning test is gradually
	 * refined into more high quality unit tests. Sometimes this is necessary
	 * because writing unit tests itself requires refactoring to make the code more
	 * testable (e.g. dependency injection), and you need a temporary end-to-end
	 * pinning test to protect the code base meanwhile.
	 * 
	 * For this deliverable, there is no reason you cannot write unit tests for
	 * pinning tests as the dependency injection(s) has already been done for you.
	 * You are required to localize each pinning unit test within the tested class
	 * as we did for Deliverable 2 (meaning it should not exercise any code from
	 * external classes). You will have to use Mockito mock objects to achieve this.
	 * 
	 * Also, you may have to use behavior verification instead of state verification
	 * to test some methods because the state change happens within a mocked
	 * external object. Remember that you can use behavior verification only on
	 * mocked objects (technically, you can use Mockito.verify on real objects too
	 * using something called a Spy, but you wouldn't need to go to that length for
	 * this deliverable).
	 */

	/* TODO: Declare all variables required for the test fixture. */

	@Mock
	Cell[][]  oPanelCells = new Cell[5][5];
	
	MainPanel oMainPanel;
	@Before
	public void setUp() 
	{
	  
	  
	  oMainPanel  = new MainPanel(5);
	  
	  oPanelCells[0][0] = Mockito.mock(Cell.class);
	  oPanelCells[0][1] = Mockito.mock(Cell.class);
	  oPanelCells[0][2] = Mockito.mock(Cell.class);
	  oPanelCells[0][3] = Mockito.mock(Cell.class);
	  oPanelCells[0][4] = Mockito.mock(Cell.class);
	  oPanelCells[1][0] = Mockito.mock(Cell.class);
	  oPanelCells[1][1] = Mockito.mock(Cell.class);
	  oPanelCells[1][2] = Mockito.mock(Cell.class);
	  oPanelCells[1][3] = Mockito.mock(Cell.class);
	  oPanelCells[1][4] = Mockito.mock(Cell.class);
	  oPanelCells[2][0] = Mockito.mock(Cell.class);
	  oPanelCells[2][1] = Mockito.mock(Cell.class);
	  oPanelCells[2][2] = Mockito.mock(Cell.class);
	  oPanelCells[2][3] = Mockito.mock(Cell.class);
	  oPanelCells[2][4] = Mockito.mock(Cell.class);
	  oPanelCells[3][0] = Mockito.mock(Cell.class);
	  oPanelCells[3][1] = Mockito.mock(Cell.class);
	  oPanelCells[3][2] = Mockito.mock(Cell.class);
	  oPanelCells[3][3] = Mockito.mock(Cell.class);
	  oPanelCells[3][4] = Mockito.mock(Cell.class);
	  oPanelCells[4][0] = Mockito.mock(Cell.class);
	  oPanelCells[4][1] = Mockito.mock(Cell.class);
	  oPanelCells[4][2] = Mockito.mock(Cell.class);
	  oPanelCells[4][3] = Mockito.mock(Cell.class);
	  oPanelCells[4][4] = Mockito.mock(Cell.class);
	  Mockito.when(oPanelCells[0][0].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[0][1].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[0][2].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[0][3].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[0][4].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[1][0].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[1][1].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[1][2].getAlive()).thenReturn(true);
	  Mockito.when(oPanelCells[1][3].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[1][4].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[2][0].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[2][1].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[2][2].getAlive()).thenReturn(true);
	  Mockito.when(oPanelCells[2][3].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[2][4].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[3][0].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[3][1].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[3][2].getAlive()).thenReturn(true);
	  Mockito.when(oPanelCells[3][3].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[3][4].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[4][0].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[4][1].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[4][2].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[4][3].getAlive()).thenReturn(false);
	  Mockito.when(oPanelCells[4][4].getAlive()).thenReturn(false);
		 
	  oMainPanel.setCells(oPanelCells);
	}
	
	/**
	 * Test case for CalculateNextIteration().
	 * Preconditions: All of the panel cells are configured to either true or false to form the initial vertical space.
	 * Execution steps: Call calculateNextIteration on the main panel, verify that setAlive(true) or setAlive(false) is called on each PanelCell.
	 * Postconditions: MainPanel._r = 1000.
	 */
	@Test
	public void testCalculateNextIteration()
	{
      oMainPanel.calculateNextIteration();
	  
	  Mockito.verify(oPanelCells[0][0]).setAlive(false);
	  Mockito.verify(oPanelCells[0][1]).setAlive(false);
	  Mockito.verify(oPanelCells[0][2]).setAlive(false);
	  Mockito.verify(oPanelCells[0][3]).setAlive(false);
	  Mockito.verify(oPanelCells[0][4]).setAlive(false);
	  Mockito.verify(oPanelCells[1][0]).setAlive(false);
	  Mockito.verify(oPanelCells[1][1]).setAlive(false);
	  Mockito.verify(oPanelCells[1][2]).setAlive(false);
	  Mockito.verify(oPanelCells[1][3]).setAlive(false);
	  Mockito.verify(oPanelCells[1][4]).setAlive(false);
	  Mockito.verify(oPanelCells[2][0]).setAlive(false);
	  Mockito.verify(oPanelCells[2][1]).setAlive(true);
	  Mockito.verify(oPanelCells[2][2]).setAlive(true);
	  Mockito.verify(oPanelCells[2][3]).setAlive(true);
	  Mockito.verify(oPanelCells[2][4]).setAlive(false);
	  Mockito.verify(oPanelCells[3][0]).setAlive(false);
	  Mockito.verify(oPanelCells[3][1]).setAlive(false);
	  Mockito.verify(oPanelCells[3][2]).setAlive(false);
	  Mockito.verify(oPanelCells[3][3]).setAlive(false);
	  Mockito.verify(oPanelCells[3][4]).setAlive(false);
	  Mockito.verify(oPanelCells[4][0]).setAlive(false);
	  Mockito.verify(oPanelCells[4][1]).setAlive(false);
	  Mockito.verify(oPanelCells[4][2]).setAlive(false);
	  Mockito.verify(oPanelCells[4][3]).setAlive(false);
	  Mockito.verify(oPanelCells[4][4]).setAlive(false);
	  
	  assertEquals(oMainPanel._r, 1000);
	}
	
	/**
	 * Test case for IterateCell().
	 * Preconditions: All of the panel cells are configured to either true or false to form the initial vertical space.
	 * Execution steps: Call iterateCell on each cell in the main panel, check to see if each cell returns true or false depending on the expected output
	 * Postconditions: Cell(2,1), Cell(2,2) and Cell(2,3) are all true, the rest are false
	 */
	@Test
	public void testIterateCell()
	{
		assertEquals(oMainPanel.iterateCell(0, 0), false);
		assertEquals(oMainPanel.iterateCell(0, 1), false);
		assertEquals(oMainPanel.iterateCell(0, 2), false);
		assertEquals(oMainPanel.iterateCell(0, 3), false);
		assertEquals(oMainPanel.iterateCell(0, 4), false);
		assertEquals(oMainPanel.iterateCell(1, 0), false);
		assertEquals(oMainPanel.iterateCell(1, 1), false);
		assertEquals(oMainPanel.iterateCell(1, 2), false);
		assertEquals(oMainPanel.iterateCell(1, 3), false);
		assertEquals(oMainPanel.iterateCell(1, 4), false);
		assertEquals(oMainPanel.iterateCell(2, 0), false);
		assertEquals(oMainPanel.iterateCell(2, 1), true);
		assertEquals(oMainPanel.iterateCell(2, 2), true);
		assertEquals(oMainPanel.iterateCell(2, 3), true);
		assertEquals(oMainPanel.iterateCell(2, 4), false);
		assertEquals(oMainPanel.iterateCell(3, 0), false);
		assertEquals(oMainPanel.iterateCell(3, 1), false);
		assertEquals(oMainPanel.iterateCell(3, 2), false);
		assertEquals(oMainPanel.iterateCell(3, 3), false);
		assertEquals(oMainPanel.iterateCell(3, 4), false);
		assertEquals(oMainPanel.iterateCell(4, 0), false);
		assertEquals(oMainPanel.iterateCell(4, 1), false);
		assertEquals(oMainPanel.iterateCell(4, 2), false);
		assertEquals(oMainPanel.iterateCell(4, 3), false);
		assertEquals(oMainPanel.iterateCell(4, 4), false);
	}
	
	/**
	 * Test case for Cell.toString().
	 * Preconditions: A true/alive cell, a false/dead cell, and an empty/unknown cell.
	 * Execution steps: Call toString on each cell.
	 * Postconditions: aliveCell.toString() = "X", deadCell.toString() = ".", unknownCell.toString() = "."
	 */
	@Test
	public void testCell()
	{
		Cell aliveCell = new Cell(true);
		Cell deadCell = new Cell(false);
		Cell unknownCell = new Cell();
		
		assertEquals(aliveCell.toString(), "X");
		assertEquals(deadCell.toString(), ".");
		assertEquals(unknownCell.toString(), ".");
	}

	/* TODO: Write the three pinning unit tests for the three optimized methods */

}
