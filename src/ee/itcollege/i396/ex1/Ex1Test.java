package ee.itcollege.i396.ex1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex1Test {

	@Test
	public void testCoverage1() {
		Ex1 ex = new Ex1();
		
		assertEquals(ex.coverage1(1, 0), 1);
		assertEquals(ex.coverage1(1, 2), 2);
		assertEquals(ex.coverage1(1, 3), 2);
		
		assertEquals(ex.coverage1(2, 0), 2);
		assertEquals(ex.coverage1(2, 3), 2);
		
		assertEquals(ex.coverage1(0, 0), 2);
		assertEquals(ex.coverage1(0, 4), 2);
	}

	@Test
	public void testCoverage2() {
		Ex1 ex = new Ex1();
		
		assertEquals(ex.coverage2(new int[] {}, 0), 1);
			assertEquals(ex.coverage2(new int[] {1}, 0), 2);
				assertEquals(ex.coverage2(new int[] {0, 1}, 2), 3);	
				
				assertEquals(ex.coverage2(new int[] {0, 2}, 1), 1);
					assertEquals(ex.coverage2(new int[] {0, 1, 2}, 1), 3);
	}
}
