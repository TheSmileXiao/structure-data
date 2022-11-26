package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	
	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() {
		S1.remove("ABC", 0);
	}
	// TODO AÑADIR MAS TESTS
	
	@Test(expected = NullPointerException.class)
	public  void testAddNullPointerException() throws Exception{
		S1.add(null, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public  void testAddIllegalArgumentException() throws Exception{
		S1.add("asd", 0);
	}
	
	@Test
	public void testAddExistantMultipleInstances() throws Exception{
		S2.add("123", 5);
		Assert.assertEquals(S2.count("123"), 10);
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNull() throws Exception{
		S1.remove(null, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveIllegalArgumentException() throws Exception{
		S2.remove("ABC", -2);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveNoSuchElementException() throws Exception{
		S2.remove("qwe",3);
	}
	
	@Test
	public void testRemove() {
		S2.remove("ABC",3);
		Assert.assertEquals(S2.count("ABC"), 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveBigTime() throws Exception{
		S2.remove("ABC",6);
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveEmpty() throws Exception{
		S1.remove();
	}
	
	@Test
	public void testRemoveNoEmpty() throws Exception {
		int i=S2.remove();
		
		Assert.assertEquals(i, 5);
	}
	
	@Test
	public void testClear() {
		S2.clear();

		Assert.assertEquals(S2.toString(), "()");
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsNullPointerException() throws Exception{
		S2.contains(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testCountNullPointerException() throws Exception{
		S2.count(null);
	}

	@Test
	public void testIterator() throws Exception{
		S1.add("ABC", 2);
		S1.add("123", 2);
		Iterator<String> iter = S1.iterator();
		Assert.assertTrue(iter.hasNext());
		iter.next();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIteratorDontHasNext() throws Exception{
		S1.add("ABC", 2);
		S1.add("123", 2);
		Iterator<String> iter = S1.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.next();

	}
		
		

}