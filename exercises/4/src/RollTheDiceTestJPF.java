import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollTheDiceTestJPF {

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
		
		// TODO: Invariant assertion 1
	}

	@Test
	public void testPlay() {
		game.play();
		// TODO: Invariant assertion 2
	}

}
