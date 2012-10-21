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

	/*
	 * @Test public void testSpareHitsAddNextPoints() throws GameOverException {
	 * 
	 * rollBall(21, 5); assertThat(calc.getScore(), is(150)); }
	 */

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
