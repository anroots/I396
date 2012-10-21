package ee.itcollege.i396.ex3;

import static org.junit.Assert.*;

import java.io.IOException;

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
	public void testSingleHitsAreRecorded() {

		for (int i = 0; i < 20; i++) {
			calc.hit(1);
		}

		assertThat(calc.getScore(), is(20));
	}

	@Test
	public void testDoubleHitsAreRecorded() {
		for (int i = 0; i < 20; i++) {
			calc.hit(2);
		}

		assertThat(calc.getScore(), is(40));
	}

	@Test
	public void testLastFrameHasThreeHits() {

		calc.setFrame(10);

		calc.hit(1);
		calc.hit(1);
		calc.hit(1);
		assertThat(calc.getFrame(), is(10));
		assertThat(calc.getScore(), is(3));
		assertThat(calc.getRoll(), is(3));
	}

	@Test
	public void testTwentyOneHitsArePossible() {
		for (int i = 0; i < 21; i++) {
			calc.hit(1);
		}

		assertThat(calc.getScore(), is(21));
	}

	@Test
	public void testGameResets() {
		for (int i = 0; i < 22; i++) {
			calc.hit(1);
		}
		assertThat(calc.getScore(), is(1));
	}

	/*
	 * @Test public void testSpareHitsAreRecorded(){ for (int i = 0; i < 21;
	 * i++) { calc.hit(5); }
	 * 
	 * assertThat(calc.getScore(), is(150)); }
	 */
}
