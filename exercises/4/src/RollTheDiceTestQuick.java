import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollTheDiceTestQuick {

	private RollTheDice game;
	
	@Before
	public void setUp() throws Exception {
		game = new RollTheDice(10, 10);
	}

	@Property(trials=100)
	public void testRound(@InRange(minInt = 0, maxInt = 5) int roll1,
			      @InRange(minInt = 0, maxInt = 5) int roll2) {
		game.round(roll1, roll2);
		// TODO: Invariant assertion 1
	}

	@Property(trials=100)
	public void testPlay() {
		game.play();
		// TODO: Invariant assertion 2
	}

}
