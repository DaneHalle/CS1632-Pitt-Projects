import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollTheDiceTestPlain {
	private RollTheDice game;
	
	@Before
	public void setUp() throws Exception {
		game = new RollTheDice(10, 10);
	}

	@Test
	public void testRound() {
		game.round(3, 5);
		switch(roll1) {
		case 0:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 9);
				assertEquals(game.getP2Chips(), 11);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 8);
				assertEquals(game.getP2Chips(), 12);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 5);
				assertEquals(game.getP2Chips(), 15);
				break;
			default:
				break;
			}
			break;
		case 1:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 11);
				assertEquals(game.getP2Chips(), 9);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 9);
				assertEquals(game.getP2Chips(), 11);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 8);
				assertEquals(game.getP2Chips(), 12);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			default:
				break;
			}
			break;
		case 2:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 11);
				assertEquals(game.getP2Chips(), 9);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 5);
				assertEquals(game.getP2Chips(), 15);
				break;
			default:
				break;
			}
			break;
		case 3:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 9);
				assertEquals(game.getP2Chips(), 11);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 8);
				assertEquals(game.getP2Chips(), 12);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 5);
				assertEquals(game.getP2Chips(), 15);
				break;
			default:
				break;
			}
			break;
		case 4:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 9);
				assertEquals(game.getP2Chips(), 11);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 8);
				assertEquals(game.getP2Chips(), 12);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 5);
				assertEquals(game.getP2Chips(), 15);
				break;
			default:
				break;
			}
			break;
		case 5:
			switch(roll2) {
			case 0:
				assertEquals(game.getP1Chips(), 10);
				assertEquals(game.getP2Chips(), 10);
				break;
			case 1:
				assertEquals(game.getP1Chips(), 9);
				assertEquals(game.getP2Chips(), 11);
				break;
			case 2:
				assertEquals(game.getP1Chips(), 8);
				assertEquals(game.getP2Chips(), 12);
				break;
			case 3:
				assertEquals(game.getP1Chips(), 7);
				assertEquals(game.getP2Chips(), 13);
				break;
			case 4:
				assertEquals(game.getP1Chips(), 6);
				assertEquals(game.getP2Chips(), 14);
				break;
			case 5:
				assertEquals(game.getP1Chips(), 5);
				assertEquals(game.getP2Chips(), 15);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		// TODO: Invariant assertion 1
	}

	@Test
	public void testPlay() {
		game.play();
		boolean coverage = (game.getP1Chips() > 0 && game.getP2Chips() <= 0) || (game.getP1Chips() <= 0 && game.getP2Chips() > 0) || (game.getP1Chips()+game.getP2Chips() <= 0);
		assertEquals(coverage, true);
		// TODO: Invariant assertion 2
	}

}
