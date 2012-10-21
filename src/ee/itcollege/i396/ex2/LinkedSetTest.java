package ee.itcollege.i396.ex2;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class LinkedSetTest {

	@Test
	public void createSizeIsZero() {
		LinkedSet ls = new LinkedSet();

		assertThat(ls.size(), is(0));
	}

	@Test
	public void newContainsIsFalse() {
		LinkedSet ls = new LinkedSet();

		assertThat(ls.contains(1), is(false));
	}

	@Test
	public void addSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		assertThat(ls.size(), is(1));
	}

	@Test
	public void containsIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		assertThat(ls.contains(1), is(true));
	}

	@Test
	public void addDuplicateSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(1);

		assertThat(ls.size(), is(1));
	}

	@Test
	public void removeLastSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.remove(1);

		assertThat(ls.size(), is(0));
	}


	@Test
	public void removeSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ls.remove(1);

		assertThat(ls.size(), is(1));
	}

	@Test(expected = IllegalStateException.class)
	public void emptyRemoveThrows() {
		LinkedSet ls = new LinkedSet();
		ls.remove(1);
	}

	// Nonexistent throws test?

	@Test
	public void newContainsAllIsFalse() {
		LinkedSet ls = new LinkedSet();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);

		assertFalse(ls.containsAll(list));
	}

	@Test
	public void setContainsAll() {
		LinkedSet ls = new LinkedSet();
		ls.add(2);
		ls.add(1);
		ls.add(6);
		ls.add(7);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(7);

		assertTrue(ls.containsAll(list));
	}

}
