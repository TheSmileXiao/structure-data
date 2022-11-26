package ule.edi.recursiveList;



import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableException;
import ule.edi.model.Objeto;



public class UnorderedLinkedListTests {

	private UnorderedLinkedListImpl<String> lS;
	private UnorderedLinkedListImpl<String> lSABC;
	
	
	@Before
	public void setUp() {
		this.lS = new UnorderedLinkedListImpl<String>();
		
		
		this.lSABC = new UnorderedLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }

// TESTS DE addFirst
   @Test
   public void addFirstTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addFirst("C");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addFirst("B");
	   Assert.assertEquals("(B C D )", lS.toString());
	   lS.addFirst("A");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   // TESTS DE addBefore
   
   @Test
   public void addBeforeTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addBefore("C", "D");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addBefore("A","C");
	   Assert.assertEquals("(A C D )", lS.toString());
	   lS.addBefore("B", "C");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   //Tests toStringReverse 
 
   @Test
   public void toStringReverse(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   
	   Assert.assertEquals("(A B C D )", lS.toString());
	   Assert.assertEquals("(D C B A )", lS.toStringReverse());
		  
   }
// Tests eliminar duplicados
	
   @Test
	public void removeDuplicatesTest1() throws EmptyCollectionException {
	   
	    UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
	    
	    
	    lista.removeDuplicates();
		//Assert.assertEquals(lista.removeDuplicates(),4); 
		Assert.assertEquals(lista.toString(), "(A B C )");
		Assert.assertEquals(lSABC.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lSABC.toString(), "(A B C )");	
	}
  
   
   
// AÑADIR MAS TESTS para el resto de casos especiales y para el resto de métodos
 // de las clases AbstractLinkedListImpl y UnorderedLinkedListImpl
   
   @Test(expected=EmptyCollectionException.class)
   public void removeDuplicatesTest2() throws EmptyCollectionException {
	   lS.removeDuplicates();
	   
   }

@Test(expected=NullPointerException.class)
   public void testContains() {
	   Assert.assertFalse(lS.contains("A"));
	   Assert.assertTrue(lSABC.contains("A"));
	   Assert.assertTrue(lSABC.contains("C"));
	   Assert.assertFalse(lSABC.contains("X"));
	   lS.contains(null);
   }
   
   @Test(expected=NullPointerException.class)
   public void testCount() {
	   
	   UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
	   
	   Assert.assertEquals(lista.count("A"), 3);
	   Assert.assertEquals(lista.count("B"), 2);
	   Assert.assertEquals(lista.count("C"), 2);
	   Assert.assertEquals(lS.count("A"), 0);
	   lista.count(null);
	   
   }
   
   
   @Test(expected=EmptyCollectionException.class)
   public void testGetLast() throws EmptyCollectionException {
	   
	   UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
	   UnorderedLinkedListImpl<String> lista1=new UnorderedLinkedListImpl<String>("B"); 

	   Assert.assertEquals(lSABC.getLast(), "C");
	   Assert.assertEquals(lista.getLast(), "C");
	   Assert.assertEquals(lista1.getLast(), "B");

	   lS.getLast();

   }
   
   @Test
   public void testIsOrdered() {
	   
	   UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
	   
	   Assert.assertTrue(lSABC.isOrdered());
	   Assert.assertTrue(lS.isOrdered());
	   Assert.assertFalse(lista.isOrdered());
	   
   }
   
   @Test(expected=NullPointerException.class)
   public void testRemove1() throws EmptyCollectionException {
	   
	   Assert.assertEquals(lSABC.remove("C"), "C");
	   Assert.assertEquals(lSABC.remove("A"), "A");
	   Assert.assertEquals(lSABC.toString(), "(B )");
	   lSABC.remove(null);



   }
   
   @Test(expected=EmptyCollectionException.class)
   public void testRemove2() throws EmptyCollectionException {
	   lS.remove("A");
   }
   
   @Test(expected=NoSuchElementException.class)
   public void testRemove3() throws EmptyCollectionException {
	   lSABC.remove("D");
	   
   }
   
   @Test(expected=NullPointerException.class)
   public void testRemoveLast1() throws EmptyCollectionException {
	   
	   lSABC.removeLast(null);

   }
   
   @Test(expected=EmptyCollectionException.class)
   public void testRemoveLast2() throws EmptyCollectionException {
	   
	   lS.removeLast("A");

   }
   
   @Test(expected=NoSuchElementException.class)
   public void testRemoveLast3() throws EmptyCollectionException {
	   
	   lSABC.removeLast("D");

   }
   
   @Test
   public void testRemoveLast4() throws EmptyCollectionException {
	   
	   UnorderedLinkedListImpl<String> lista;
	   lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
	   
	   Assert.assertEquals(lSABC.removeLast("C"), "C");
	   Assert.assertEquals(lSABC.toString(), "(A B )");
	   Assert.assertEquals(lSABC.removeLast("B"), "B");
	   Assert.assertEquals(lSABC.toString(), "(A )");
	   
	   Assert.assertEquals(lista.removeLast("A"), "A");
	   Assert.assertEquals(lista.removeLast("A"), "A");
	   Assert.assertEquals(lista.removeLast("A"), "A");
	   Assert.assertEquals(lista.removeLast("B"), "B");
	   Assert.assertEquals(lista.toString(), "(B C C )");

	   




   }
   
   @Test
   public void testSize() {
	   Assert.assertEquals(lSABC.size(), 3);
	   Assert.assertEquals(lS.size(), 0);

   }
   
   @Test(expected=EmptyCollectionException.class)
   public void testGetFirst() throws EmptyCollectionException {
	   Assert.assertEquals(lSABC.getFirst(), "A");
	   lS.getFirst();
   }

   @Test(expected=IllegalArgumentException.class)
   public void toStringFromUntilTest() {
	   Assert.assertEquals(lSABC.toStringFromUntil(1,3), "(A B C )");
	   Assert.assertEquals(lSABC.toStringFromUntil(3,3), "(C )");
	   Assert.assertEquals(lSABC.toStringFromUntil(1,9), "(A B C )");
	   Assert.assertEquals(lSABC.toStringFromUntil(1,2), "(A B )");
	   Assert.assertEquals(lSABC.toStringFromUntil(5,10), "()");
	   lSABC.toStringFromUntil(-1, 3);



   }
   
   @Test(expected=UnsupportedOperationException.class)
   public void iteradorTest() {
	   Iterator<String> iter = lSABC.iterator();
	   Assert.assertTrue(iter.hasNext());
	   Assert.assertEquals(iter.next().toString(), "A");
	   Assert.assertTrue(iter.hasNext());
	   Assert.assertEquals(iter.next().toString(), "B");
	   Assert.assertTrue(iter.hasNext());
	   Assert.assertEquals(iter.next().toString(), "C");
	   Assert.assertFalse(iter.hasNext());
	   iter.remove();

   }

   @Test(expected = NullPointerException.class)
   public void addFirstExceptionTest() {
	   lS.addFirst(null);
   }
   
   @Test(expected = NullPointerException.class)
   public void addLastExceptionTest() {
	   lS.addLast(null);
   }
   
   @Test(expected = NullPointerException.class)
   public void addBefoeTest1() {
	   lSABC.addBefore("D", "A");
	   lSABC.addBefore("C", "B");
	   
	   Assert.assertEquals(lSABC.toString(),"(D A C B C )");
	   
	   lSABC.addBefore(null, "B");

   }
   
   @Test(expected = NoSuchElementException.class)
   public void addBefoeTest2() {
	   
	   lSABC.addBefore("D", "D");
   }
   
   @Test(expected = TypeIsNotComparableException.class)
   public void isOrderedExceptionTest() {
	   
	   Objeto objeto1 = new Objeto("coche");
	   Objeto objeto2 = new Objeto("arbol");

	   UnorderedLinkedListImpl<Objeto> lista =new UnorderedLinkedListImpl<Objeto>(objeto1,objeto2);

	   lista.isOrdered();
	   

	   
   }
	
 

}
