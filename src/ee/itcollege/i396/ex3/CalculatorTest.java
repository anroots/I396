package ee.itcollege.i396.ex3;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import ee.itcollege.i396.ex3.Calculator.GameOverException;

public class CalculatorTest {

	@Test
	public void newCalcScoreIsZero() throws GameOverException {
		Calculator calc = new Calculator();
		assertThat(calc.getScore(), is(0));
	}

	@Test
	public void singleHitScoreIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(1);
		assertThat(calc.getScore(), is(1));
	}

	@Test
	public void frameGetSpareIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(5);
		calc.hit(5);
		assertThat(calc.getFrames().get(calc.getFrameIndex()).isSpare(),
				is(true));
	}

	@Test
	public void frameGetStrikeIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(10);

		assertThat(calc.getFrames().get(calc.getFrameIndex()).isStrike(),
				is(true));
	}

	@Test
	public void strikeHitCorrectFrames() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(10);
		calc.hit(1);

		assertThat(calc.getFrameIndex(), is(1));
	}

	@Test
	public void spareScoreIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(5);
		calc.hit(5);
		calc.hit(1);

		assertThat(calc.getFrames().get(0).getScore(), is(11));
	}

	@Test
	public void strikeScore1IsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(10);
		calc.hit(10);
		calc.hit(10);

		assertThat(calc.getFrames().get(0).getScore(), is(30));
	}

	@Test
	public void strikeScore2IsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		calc.hit(1);
		calc.hit(2);

		calc.hit(10);

		calc.hit(2);
		calc.hit(3);

		assertThat(calc.getFrames().get(0).getScore(), is(3));
		assertThat(calc.getFrames().get(1).getScore(), is(15));
	}

	@Test
	public void singleHitsScoreIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();

		rollBall(calc, 20, 1);

		assertThat(calc.getScore(), is(20));
	}

	@Test
	public void doubleHitsScoreIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		rollBall(calc, 20, 2);

		assertThat(calc.getScore(), is(40));
	}

	@Test
	public void fiveHitsScoreIsCorrect() throws GameOverException {
		Calculator calc = new Calculator();
		rollBall(calc, 21, 5);

		assertThat(calc.getScore(), is(150));
	}

	@Test
	public void testNormalGameYieldsExpectedPoints() throws GameOverException {
		Calculator calc = new Calculator();

		calc.hit(3);
		calc.hit(5);

		calc.hit(5);
		calc.hit(5);

		calc.hit(2);
		calc.hit(5);

		calc.hit(10);

		calc.hit(2);
		calc.hit(8);

		calc.hit(10);

		calc.hit(5);
		calc.hit(2);

		calc.hit(7);
		calc.hit(1);

		calc.hit(3);
		calc.hit(7);

		calc.hit(10);
		calc.hit(7);
		calc.hit(3);

		assertThat(calc.getScore(), is(139));
	}

	@Test(expected = GameOverException.class)
	public void tooManyScoresThrows() throws GameOverException {
		Calculator calc = new Calculator();

		rollBall(calc, Calculator.NUMBER_OF_FRAMES_IN_GAME * 2 + 3, 2);
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
	private void rollBall(Calculator calc, int times, int hit)
			throws GameOverException {
		for (int i = 0; i < times; i++) {
			calc.hit(hit);
		}
	}
}
