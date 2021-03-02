package exam;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gov.nasa.jpf.vm.Verify;

public class RollTheDiceJPF {
	private RollTheDice game;
	
	@Before
	public void setUp() throws Exception {
		game = new RollTheDice(10, 10);
	}

	@Test
	public void testRound() {
		int roll1 = Verify.getInt(0, 5);
		int roll2 = Verify.getInt(0, 5);
		game.round(roll1, roll2);
	}

	@Test
	public void testPlay() {
		game.play();
	}
}