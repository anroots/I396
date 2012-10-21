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
		assertTrue(calc.currentFrameIsSpare());
	}

	@Test
	public void testFrameIsNotSpare() throws GameOverException {
		rollBall(2, 4);
		assertFalse(calc.currentFrameIsSpare());
	}

	@Test
	public void testSpareHitAddsNextThrowPoints() throws GameOverException {
		calc.hit(7);
		calc.hit(3); // It's a spare!
		calc.hit(2);
		assertThat(calc.getScore(), is(14)); // (10 + 2) + 2
	}

	@Test
	public void testSpareHitAddsOnlyFirstRoll() throws GameOverException {
		calc.hit(5);
		calc.hit(5);
		assertEquals(calc.getFrame(), 1);

		// It's a spare, but we're still in the spare frame

		calc.hit(2); // We're on the next frame
		assertEquals(calc.getFrame(), 2);
		calc.hit(4); // Still on the next frame
		assertEquals(calc.getFrame(), 2);
		assertThat(calc.getScore(), is(18));
	}

	@Test
	public void testStrikeSkipsToNextFrame() throws GameOverException {
		calc.hit(10);
		assertEquals(calc.getFrame(), 2);
	}

	@Test
	public void testLastFrameWasStrike() throws GameOverException {
		calc.hit(10);
		calc.hit(2);
		assertTrue(calc.lastFrameWasStrike());
		calc.hit(1);
		assertTrue(calc.lastFrameWasStrike());
		calc.hit(1);
		assertFalse(calc.lastFrameWasStrike());
	}

	@Test
	public void testStrikeIsNotSpare() throws GameOverException {
		calc.hit(10);
		assertFalse(calc.currentFrameIsSpare());
		assertTrue(calc.lastFrameWasStrike());
	}

	@Test
	public void testStrikeYieldsAdditionalPoints() throws GameOverException {
		calc.hit(10); // Strike
		calc.hit(1);
		assertThat(calc.getScore(), is(12));
		calc.hit(2);
		assertThat(calc.getScore(), is(16)); // (10 + 1 + 2) + 1 + 2
	}

	@Test
	public void testResetGame() throws GameOverException {
		rollBall(13, 2);
		calc.resetGame();
		assertThat(calc.getScore(), is(0));
		assertThat(calc.getFrame(), is(1));
		assertThat(calc.getRoll(), is(0));
	}

	// Todo: add more sample games
	@Test
	public void testNormalGameYieldsExpectedPoints() throws GameOverException {

		addFrame(3, 5);
		assertThat(calc.getScore(), is(8));

		addFrame(5, 4);
		assertThat(calc.getScore(), is(17));

		addFrame(2, 5);
		assertThat(calc.getScore(), is(24));
		assertEquals(calc.getFrame(), 3);
		assertEquals(calc.getRoll(), 2);

		addFrame(10);
		assertThat(calc.getScore(), is(34));

		addFrame(2, 1);
		assertThat(calc.getScore(), is(40));

		addFrame(2, 3);
		assertThat(calc.getScore(), is(42));

		addFrame(1, 3);
		assertThat(calc.getScore(), is(46));

		addFrame(2, 1);
		assertThat(calc.getScore(), is(49));

		addFrame(4, 4);
		assertThat(calc.getScore(), is(57));

		addFrame(1, 4, 0);
		assertThat(calc.getScore(), is(62));
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
