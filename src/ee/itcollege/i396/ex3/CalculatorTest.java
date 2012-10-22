package ee.itcollege.i396.ex3;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class CalculatorTest {
	
	@Test
	public void newCalcScoreIsZero() throws IllegalStateException {
		Calculator calc = new Calculator();
		assertThat(calc.getScore(), is(0));
	}
	
	@Test
	public void singleHitScoreIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		calc.hit(1);
		assertThat(calc.getScore(), is(1));
	}
	
	@Test
	public void frameGetSpareIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		calc.hit(5);
		calc.hit(5);
		assertThat(calc.frames.get(calc.frameIndex).isSpare(), is(true));
	}
	
	@Test
	public void frameGetStrikeIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		calc.hit(10);
		
		assertThat(calc.frames.get(calc.frameIndex).isStrike(), is(true));
	}
	
	@Test
	public void strikeHitCorrectFrames() throws IllegalStateException {
		Calculator calc = new Calculator();
		calc.hit(10);
		calc.hit(1);
		
		assertThat(calc.frameIndex, is(1));
	}
	
	@Test
	public void spareScoreIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		calc.hit(5);
		calc.hit(5);
		calc.hit(1);
		
		assertThat(calc.frames.get(0).getScore(), is(11));
	}
	
	@Test
	public void strikeScore1IsCorrect() throws IllegalStateException{
		Calculator calc = new Calculator();
		calc.hit(10);
		calc.hit(10);
		calc.hit(10);
		
		assertThat(calc.frames.get(0).getScore(), is(30));
	}
	
	@Test
	public void strikeScore2IsCorrect() throws IllegalStateException{
		Calculator calc = new Calculator();
		calc.hit(1);
		calc.hit(2);
		
		calc.hit(10);
		
		calc.hit(2);
		calc.hit(3);
		
		assertThat(calc.frames.get(0).getScore(), is(3));
		assertThat(calc.frames.get(1).getScore(), is(15));
	}

	@Test
	public void singleHitsScoreIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();

		rollBall(calc, 20, 1);

		assertThat(calc.getScore(), is(20));
	}

	@Test
	public void doubleHitsScoreIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		rollBall(calc, 20, 2);

		assertThat(calc.getScore(), is(40));
	}
	
	@Test
	public void fiveHitsScoreIsCorrect() throws IllegalStateException {
		Calculator calc = new Calculator();
		rollBall(calc, 21, 5);

		assertThat(calc.getScore(), is(150));
	}
	
	@Test
	public void testNormalGameYieldsExpectedPoints() throws IllegalStateException {
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
	
	@Test(expected = IllegalStateException.class)
	public void tooManyScoresThrows() throws IllegalStateException {
		Calculator calc = new Calculator();
		
		rollBall(calc, 12, 10);
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
	private void rollBall(Calculator calc, int times, int hit) throws IllegalStateException {
		for (int i = 0; i < times; i++) {
			calc.hit(hit);
		}
	}
}
