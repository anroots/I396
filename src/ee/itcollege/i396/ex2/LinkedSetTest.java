package ee.itcollege.i396.ex2;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

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

	@Test
	public void newContainsAllIsFalse() {
		LinkedSet ls = new LinkedSet();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);

		assertFalse(ls.containsAll(list));
	}

	@Test
	public void containsAllIsCorrect() {
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
	
	@Test
	public void newAsListIsCorrect() {
		LinkedSet ls = new LinkedSet();

		assertThat(ls.asList(), is(asList()));
	}
	
	@Test
	public void asListIsReturnedInOrder() {
		LinkedSet ls = new LinkedSet();
		ls.add(2);
		ls.add(1);
		ls.add(6);

		List<Object> returned = ls.asList();
		List<Object> expected = asList((Object)2, (Object)1, (Object)6);

		assertThat(returned, is(expected));
	}
	
	@Test(expected = IllegalStateException.class)
	public void emptyRemoveAllThrows() {
		LinkedSet ls = new LinkedSet();
		ls.removeAll(asList((Object)1));
	}

	@Test
	public void removeAllisCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(5);
		ls.add(1);
		ls.add(2);
		ls.add(4);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);

		ls.removeAll(list);
		List<Object> expected = asList((Object)5, (Object)4);
		
		assertThat(ls.asList(), is(expected));
		assertThat(ls.size(), is(2));
	}
	
	@Test
	public void removeAllExTrueIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);

		assertThat(ls.removeAll(list), is(true));
		assertThat(ls.size(), is(0));
	}
	
	@Test
	public void removeAllExFalseIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);

		assertThat(ls.removeAll(list), is(false));
		assertThat(ls.size(), is(1));
	}

	@Test(expected = IllegalStateException.class)
	public void emptyRetainAllThrows() {
		LinkedSet ls = new LinkedSet();
		ls.retainAll(asList((Object)1));
	}

	@Test
	public void retainAllisCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(5);
		ls.add(1);
		ls.add(2);
		ls.add(4);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);

		ls.retainAll(list);
		List<Object> expected = asList((Object)1, (Object)2);

		assertThat(ls.asList(), is(expected));
		assertThat(ls.size(), is(2));
	}
	
	@Test
	public void retainAllExTrueIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);

		assertThat(ls.retainAll(list), is(true));
		assertThat(ls.size(), is(0));
	}
	
	@Test
	public void retainAllExFalseIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);

		assertThat(ls.retainAll(list), is(false));
		assertThat(ls.size(), is(1));
	}
}
