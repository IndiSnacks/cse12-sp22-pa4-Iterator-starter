
/**
 * Name: Sahil Gathe
 * ID: A16840774
 * Email:sgathe@ucsd.edu	
 * Sources used: Tutors, Zybooks, and Lecture Slides
 * 
 * This File is used to test the implmetation of the ittrator. 
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * This class is used to test the linked list ittrator 
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	private MyLinkedList listLen1, listLen3;
    private MyLinkedList.MyListIterator listLen1Iter, listLen3Iter;

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
	listLen1 = new MyLinkedList();
	listLen1.add("one");
	listLen1Iter = listLen1.new MyListIterator();

	listLen3 = new MyLinkedList();
	listLen3.add("one");
	listLen3.add("two");
	listLen3.add("three");
	listLen3Iter = listLen3.new MyListIterator();
	}

	/**
	 * TODO: test the hasNext method when [Test through a whole linked list]
	 */
	@Test
	public void testHasNext() {
		assertTrue(listLen3Iter.hasNext());
		listLen3Iter.next(); //point to two
		assertTrue(listLen3Iter.hasNext());
		listLen3Iter.next(); //point to three
		assertTrue(listLen3Iter.hasNext());
		listLen3Iter.next(); //point to tail
		assertFalse(listLen3Iter.hasNext());
	}

	/**
	 * TODO: test the next method when [there is no next node]
	 */
	@Test
	public void testNext() {
		listLen1Iter.next();
		boolean error = false;
		try{
			listLen1Iter.next();
		}catch(NoSuchElementException bad){
			error = true;
		}
		assertTrue("Error was caught", error);
	}

	/**
	 * TODO: test the hasPrevious method when [through a whole linked list]
	 */
	@Test
	public void testHasPrevious() {
		listLen3Iter.next(); //point to two
		listLen3Iter.next(); //point to three
		listLen3Iter.next(); //point to tail
		assertTrue(listLen3Iter.hasPrevious());
		listLen3Iter.previous(); //point to two
		assertTrue(listLen3Iter.hasPrevious());
		listLen3Iter.previous(); //point to one
		assertTrue(listLen3Iter.hasPrevious());
		listLen3Iter.previous(); //point to head
		assertFalse(listLen3Iter.hasPrevious());
	}

	/**
	 * TODO: test the previous method when [the linked list is empty]
	 */
	@Test
	public void testPrevious() {
		listLen1.clear(); //clears the linked list
		boolean error = false;
		try{
			listLen1Iter.previous();
		}catch(NoSuchElementException bad){
			error = true;
		}
		assertTrue(error);
	}

	/**
	 * TODO: test the nextIndex method when [going through the whole linked list]
	 */
	@Test
	public void testNextIndex() {
		assertEquals(0, listLen3Iter.nextIndex()); //testing initial index
		listLen3Iter.next();
		assertEquals(1, listLen3Iter.nextIndex()); //testing first index
		listLen3Iter.next();
		assertEquals(2, listLen3Iter.nextIndex()); //testing secound index
		listLen3Iter.next();
		assertEquals(3, listLen3Iter.nextIndex()); //testing third index
	}

	/**
	 * TODO: test the previousIndex method when [going thorugh the whole linked list]
	 */
	@Test
	public void testPreviousIndex() {
		listLen3Iter.next();
		listLen3Iter.next();
		listLen3Iter.next(); //end of the linked list 
		assertEquals(2, listLen3Iter.previousIndex()); //test 2nd index
		listLen3Iter.previous();
		assertEquals(1, listLen3Iter.previousIndex()); //test 1st index
		listLen3Iter.previous();
		assertEquals(0, listLen3Iter.previousIndex()); //test 0 index
	}

	/**
	 * TODO: test the set method when [setting a null element going foward and backward]
	 */
	@Test
	public void testSet() {
		listLen1Iter.forward = true;
		boolean error = false;
		try{
			listLen1Iter.set(null);
		}catch(NullPointerException bad){
			error = true;
		}
		assertTrue(error);

		listLen1Iter.forward = false;
		boolean error2 = false;
		try{
			listLen1Iter.set(null);
		}catch(NullPointerException bad){
			error2 = true;
		}
		assertTrue(error2);
	}

	/**
	 * TODO: test the remove method when [canSetandRemove is false]
	 */
	@Test
	public void testRemoveTestOne() {
		listLen1Iter.canRemoveOrSet = false;
		boolean error = false;
		try{
			listLen1Iter.remove();
		}catch(IllegalStateException bad){
			error = true;
		}
		assertTrue(error);
	}

	/**
	 * TODO: test the remove method when [remove empty linked list]
	 */
	@Test
	public void testRemoveTestTwo() {
		listLen1.clear();
		boolean error = false;
		try{
			listLen1Iter.remove();
		}catch(IllegalStateException bad){
			error = true;
		}
		assertTrue(error);
	}

	/**
	 * TODO: test the add method when [adding a null element]
	 */
	@Test
	public void testAdd() {
		boolean error = false;
		try{
			listLen1Iter.add(null);
		}catch(NullPointerException bad){
			error = true;
		}
		assertTrue(error);
	}

}