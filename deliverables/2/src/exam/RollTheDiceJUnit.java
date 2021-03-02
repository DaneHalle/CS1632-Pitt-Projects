package exam;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RollTheDiceJUnit {
	private RollTheDice game;
	
	@Before
	public void setUp() throws Exception {
		game = new RollTheDice(10, 10);
	}

	@Test
	public void testRound() {
		game.round(3, 5);
		if(game.getP1Chips() <= 0)
		{
			assertTrue(game.getP2Chips() > 0);
		}
		else if (game.getP2Chips() <= 0)
		{
			assertTrue(game.getP1Chips() > 0);
		}
	}

	@Test
	public void testPlay() {
		game.play();
		assertTrue((game.getP2Chips() <= 0 && game.getP1Chips() > 0) || (game.getP1Chips() <= 0 && game.getP2Chips() > 0));
	}
}
