package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;
	
@Before
public void antesDe() {
	lv=new DoubleLinkedListImpl<String>();
	listaConElems=new DoubleLinkedListImpl<String>();
	listaConElems.insertFirst("D");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	listaConElems.insertFirst("C");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	
}


	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==6);
		
	}
	
	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		
		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));
		
	}
	
	@Test
	public void removeAllTest() throws EmptyCollectionException {
        Assert.assertEquals(2, listaConElems.removeAll("A"));
    	Assert.assertEquals(listaConElems.toString(),"(B C B D )");
    	
        listaConElems.removeAll("B");
		Assert.assertFalse(listaConElems.contains("A"));
		Assert.assertFalse(listaConElems.contains("B"));
		Assert.assertEquals(listaConElems.toString(),"(C D )");
		listaConElems.removeAll("C");
		
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("C"));
        listaConElems.removeAll("D");
		
		Assert.assertFalse(listaConElems.contains("D"));
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void isSubListTest() throws EmptyCollectionException {
		 Assert.assertTrue(listaConElems.isSubList(lv));
	    	Assert.assertTrue(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "B", "C")));
	      	Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
	      	Assert.assertEquals(new DoubleLinkedListImpl<String>("A", "C").toString(),"(A C )");   
	     	Assert.assertFalse(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "C")));
	     	Assert.assertEquals(listaConElems.maxRepeated(),2);
	     	listaConElems.insertBefore("A", "D");
	    	Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
	    	Assert.assertTrue(listaConElems.maxRepeated()==3);
	        	  
	}
	
	@Test(expected=NullPointerException.class)
	public void insertNullExceptionTest() {
		listaConElems.insertFirst(null);		
	}
	
	@Test(expected=NullPointerException.class)
	public void insertNullExceptionTest1() {
		listaConElems.insertLast(null);		
	}
	
	@Test(expected=NullPointerException.class)
	public void insertNullExceptionTest2() {
		listaConElems.insertPos(null, 1);
	}
	
	@Test(expected=NullPointerException.class)
	public void insertNullExceptionTest3() {
		listaConElems.insertBefore(null, "A");
	}
	
	@Test(expected=NullPointerException.class)
	public void getPosNullExceptionTest() {
		lv.getPosFirst(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void getPosNullExceptionTest1() {
		lv.getPosLast(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void removeAllNullExceptionTest() {
		lv.removeAll(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void containsNullExceptionTest() {
		lv.contains(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void isEqualsNullExceptionTest() {
		lv.isEquals(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void containsAllNullExceptionTest() {
		lv.containsAll(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void isSubListNullExceptionTest() {
		lv.isSubList(null);
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void remomveFirstTest() throws EmptyCollectionException {
		listaConElems.removeFirst();
		listaConElems.removeFirst();
		listaConElems.removeFirst();
		listaConElems.removeFirst();
		listaConElems.removeFirst();
		listaConElems.removeFirst();
		listaConElems.removeFirst();
	}

	@Test(expected=EmptyCollectionException.class)
	public void remomveLastTest() throws EmptyCollectionException {
		listaConElems.removeLast();
		listaConElems.removeLast();
		listaConElems.removeLast();
		listaConElems.removeLast();
		listaConElems.removeLast();
		listaConElems.removeLast();
		listaConElems.removeLast();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void insertPosTest() {
		String elem= "a";
		lv.insertPos(elem, 1);
		lv.insertPos(elem, 1);
		lv.insertPos(elem, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getElemPosTest() {
		listaConElems.getElemPos(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getElemPosITest1() {
		listaConElems.getElemPos(6);
		listaConElems.getElemPos(7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removePosIllegal() {
		listaConElems.removePos(6);
		listaConElems.removePos(6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removePosIllegal1() {
		listaConElems.removePos(6);
		listaConElems.removePos(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilTest() {
		listaConElems.toStringFromUntil(1, 6);
		Assert.assertEquals("(A B C A B D )",listaConElems.toStringFromUntil(1, 6));
		Assert.assertEquals("(C A B D )",listaConElems.toStringFromUntil(3, 6));
		Assert.assertEquals("(C A B D )",listaConElems.toStringFromUntil(3, 20));
		Assert.assertEquals("()",listaConElems.toStringFromUntil(10, 20));
		listaConElems.toStringFromUntil(0, 2);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void getPosFirstTest() {
		Assert.assertEquals(1,listaConElems.getPosFirst("A"));
		Assert.assertEquals(2,listaConElems.getPosFirst("B"));
		Assert.assertEquals(3,listaConElems.getPosFirst("C"));
		Assert.assertEquals(6,listaConElems.getPosFirst("D"));
		lv.getPosFirst("A");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void getPosLastTest() {
		Assert.assertEquals(4,listaConElems.getPosLast("A"));
		Assert.assertEquals(5,listaConElems.getPosLast("B"));
		Assert.assertEquals(3,listaConElems.getPosLast("C"));
		Assert.assertEquals(6,listaConElems.getPosLast("D"));
		listaConElems.getPosLast("X");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void removeAllTest1() {
		lv.removeAll("A");
	}
	
	@Test
	public void toStringReverseTest() {
		Assert.assertEquals("(D B A C B A )",listaConElems.toStringReverse());
	}
	
	@Test
	public void reverseTest() {
		Assert.assertEquals("(D B A C B A )",listaConElems.reverse().toString());
	}
	
	@Test
	public void isEqualsTest() {
		DoubleLinkedListImpl<String> aux;
		aux=new DoubleLinkedListImpl<String>();
		
		aux.insertFirst("D");
		aux.insertFirst("B");
		aux.insertFirst("A");
		aux.insertFirst("C");
		aux.insertFirst("B");
		aux.insertFirst("A");
		
		Assert.assertFalse(listaConElems.isEquals(lv));
		Assert.assertTrue(listaConElems.isEquals(listaConElems));
		Assert.assertTrue(listaConElems.isEquals(aux));
	}
	
	@Test
	public void isEqualsTest2() {
		DoubleLinkedListImpl<String> aux;
		aux=new DoubleLinkedListImpl<String>();
		
		aux.insertFirst("D");
		aux.insertFirst("B");
		aux.insertFirst("A");
		aux.insertFirst("C");
		aux.insertFirst("C");
		aux.insertFirst("A");
		
		Assert.assertFalse(listaConElems.isEquals(lv));
		Assert.assertTrue(listaConElems.isEquals(listaConElems));
		Assert.assertFalse(listaConElems.isEquals(aux));
	}
	
	@Test
	public void containsAllTest() {
		DoubleLinkedListImpl<String> aux;
		aux=new DoubleLinkedListImpl<String>();
		
		aux.insertFirst("D");
		aux.insertFirst("B");
		aux.insertFirst("A");
		Assert.assertTrue(listaConElems.containsAll(aux));

		aux.insertFirst("X");
		Assert.assertFalse(listaConElems.containsAll(aux));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void iteratorNormTest() {
		Iterator<String> it = listaConElems.iterator();
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "A");
		Assert.assertEquals(it.next(), "B");
		Assert.assertEquals(it.next(), "C");
		Assert.assertEquals(it.next(), "A");
		Assert.assertEquals(it.next(), "B");
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "D");
		Assert.assertFalse(it.hasNext());
		it.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void iteratorReverseTest() {
		Iterator<String> it = listaConElems.reverseIterator();
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "D");
		Assert.assertEquals(it.next(), "B");
		Assert.assertEquals(it.next(), "A");
		Assert.assertEquals(it.next(), "C");
		Assert.assertEquals(it.next(), "B");
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "A");
		Assert.assertFalse(it.hasNext());
		it.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void iteratorEvenTest() {
		Iterator<String> it = listaConElems.evenPositionsIterator();
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "B");
		Assert.assertEquals(it.next(), "A");
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "D");
		Assert.assertFalse(it.hasNext());
		it.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void iteratorProgressTest() {
		Iterator<String> it = listaConElems.progressIterator();
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "A");
		Assert.assertEquals(it.next(), "B");
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals(it.next(), "A");
		Assert.assertFalse(it.hasNext());
		it.next();
	}
	
}
