package ee.itcollege.i396.ex3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class CalculatorTest {

	private Calculator calc;

	@Before
	public void setUp() {
		calc = new Calculator();
	}

	@Test
	public void testSingleHitsAreRecorded() throws GameOverException {

		rollBall(20, 1);

		assertThat(calc.getScore(), is(20));
	}

	@Test
	public void testDoubleHitsAreRecorded() throws GameOverException {
		rollBall(20, 2);

		assertThat(calc.getScore(), is(40));
	}

	@Test
	public void testLastFrameHasThreeHits() throws GameOverException {

		calc.setFrame(10);

		rollBall(3, 1);

		assertThat(calc.getFrame(), is(10));
		assertThat(calc.getScore(), is(3));
		assertThat(calc.getRoll(), is(3));
	}

	@Test
	public void testTwentyOneHitsArePossible() throws GameOverException {
		rollBall(21, 1);
		assertThat(calc.getScore(), is(21));
	}

	@Test(expected = GameOverException.class)
	public void testGameOver() throws GameOverException {
		rollBall(22, 1);
	}

	@Test
	public void testFrameIsSpare() throws GameOverException {
		rollBall(2, 5);
		assertTrue(calc.lastFrameWasSpare());
	}

	@Test
	public void testFrameIsNotSpare() throws GameOverException {
		rollBall(2, 4);
		assertFalse(calc.lastFrameWasSpare());
	}

	@Test
	public void testSpareHitsAddNextPoints() throws GameOverException {

		fail("Todo, requirements are confusing.");
		// rollBall(21, 5);
		// assertThat(calc.getScore(), is(150));
	}

	@Test
	public void testStrike() throws GameOverException {
		calc.hit(10);
		calc.hit(3);
		calc.hit(6);
		assertThat(calc.getScore(), is(28));
	}
	
	@Test
	public void testResetGame() throws GameOverException {
		rollBall(13, 2);
		calc.resetGame();
		assertThat(calc.getScore(), is(0));
		assertThat(calc.getFrame(),is(1));
		assertThat(calc.getRoll(),is(0));
	}

	@Test
	public void testNormalGameYieldsExpectedPoints() throws GameOverException {
		fail("Todo");
		addFrame(3, 5);
		addFrame(5, 5);
		addFrame(2, 5);
		addFrame(10);
		addFrame(2, 8);
		addFrame(10);
		addFrame(5, 2);
		addFrame(7, 1);
		addFrame(3, 7);
		addFrame(10, 7, 3);

		assertThat(calc.getScore(), is(139));
	}

	private void addFrame(int roll1, int roll2) throws GameOverException {
		calc.hit(roll1);
		calc.hit(roll2);
	}

	private void addFrame(int roll1, int roll2, int roll3)
			throws GameOverException {
		calc.hit(roll1);
		calc.hit(roll2);
		calc.hit(roll3);
	}

	private void addFrame(int roll1) throws GameOverException {
		calc.hit(roll1);
	}

	/**
	 * Helper method for rolling the bowling ball. Each roll hits the same
	 * number of bats.
	 * 
	 * @param times
	 *            How many times to roll the ball?
	 * @param hit
	 *            How many bats to hit with each roll?
	 */
	private void rollBall(int times, int hit) throws GameOverException {
		for (int i = 0; i < times; i++) {
			calc.hit(hit);
		}
	}
}
