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

	// Nonexistent throws test?
	
	@Test
	public void newContainsAllIsFalse() {
		LinkedSet ls = new LinkedSet();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		
		assertThat(ls.containsAll(list), is(false));
	}
	
	@Test
	public void containsAllExpTrueIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		
		assertThat(ls.containsAll(list), is(true));
	}
	
	@Test
	public void containsAllExpFalseIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);
		list.add(3);
		
		assertThat(ls.containsAll(list), is(false));
	}
	
	@Test(expected = IllegalStateException.class) 
	public void newRemoveAllThrows() {
		LinkedSet ls = new LinkedSet();
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		
		ls.removeAll(list);
	}
	
	@Test(expected = NullPointerException.class) 
	public void newRemoveAllNullListThrows() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		
		ls.removeAll(null);
	}
	
	@Test
	public void removeAllExpTrueIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ls.add(3);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);
		list.add(4);
		
		assertThat(ls.removeAll(list), is(true));
	}
	
	@Test
	public void removeAllExpFalseIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(3);
		list.add(4);
		
		assertThat(ls.removeAll(list), is(false));
	}
	
	@Test
	public void removeAllSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ls.add(3);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		ls.removeAll(list);
		
		assertThat(ls.size(), is(1));
	}
	
	@Test(expected = IllegalStateException.class) 
	public void newRetainAllThrows() {
		LinkedSet ls = new LinkedSet();
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		
		ls.retainAll(list);
	}
	
	@Test(expected = NullPointerException.class) 
	public void retainAllEmptyListThrows() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		
		ls.retainAll(null);
	}
	
	@Test
	public void retainAllExpFalseIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		
		assertThat(ls.retainAll(list), is(false));
	}
	
	@Test
	public void retainAllExpTrueIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);
		list.add(3);
		
		assertThat(ls.retainAll(list), is(true));
	}
	
	@Test
	public void retainAllSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(2);
		list.add(3);
		
		ls.retainAll(list);
		
		assertThat(ls.size(), is(1));
	}
	
	@Test
	public void newAsListIsEmpty() {
		LinkedSet ls = new LinkedSet();
		
		assertThat(ls.asList().size(), is(0));
	}
	
	@Test
	public void asListSizeIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		
		assertThat(ls.asList().size(), is(1));
	}
	
	@Test
	public void asListIsCorrect() {
		LinkedSet ls = new LinkedSet();
		ls.add(1);
		ls.add(2);
		
		assertTrue(ls.asList().equals(asList((Object)1, (Object)2)));
		assertFalse(ls.asList().equals(asList((Object)2, (Object)1)));
	}
}
