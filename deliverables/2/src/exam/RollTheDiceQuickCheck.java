package exam;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class RollTheDiceQuickCheck {
	private RollTheDice game;
	
	@Before
	public void setUp() throws Exception {
		game = new RollTheDice(10, 10);
	}

	@Property(trials=100)
	public void testRound(@InRange(minInt = 0, maxInt = 5) int roll1,
			      @InRange(minInt = 0, maxInt = 5) int roll2) {
		game.round(roll1, roll2);
		if(game.getP1Chips() <= 0)
		{
			assertTrue(game.getP2Chips() > 0);
		}
		else if (game.getP2Chips() <= 0)
		{
			assertTrue(game.getP1Chips() > 0);
		}
	}

	@Property(trials=100)
	public void testPlay() {
		game.play();
		assertTrue((game.getP2Chips() <= 0 && game.getP1Chips() > 0) || (game.getP1Chips() <= 0 && game.getP2Chips() > 0));
	}
}