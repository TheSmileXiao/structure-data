package ule.edi.recursiveList;


import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableException;
import ule.edi.model.Person;

public class OrderedLinkedListTests {

	private OrderedLinkedListImpl<String> lA4B2;
	private OrderedLinkedListImpl<String> listaConElems;
	
	private Person person1, person2, person3, person4;
	


	@Before
	public void setupFixture() {
		lA4B2 = new OrderedLinkedListImpl<String>("A", "B", "A", "A", "B", "A");
		listaConElems=new OrderedLinkedListImpl<String>("C", "X", "A", "D", "B", "Z");
		person1=new Person("10203040A", "Ana", 20);
		person2=new Person("20304050A", "Pedro", 18);
		person3=new Person("01020304A", "Sara", 16);
		person4=new Person("30405060A", "Pablo", 30);

	}
	
	@Test
	public void testConstructsEmpty() {
		Assert.assertTrue(new OrderedLinkedListImpl<>().isEmpty());
	}
	

	// tests isOrdered
	// todas las listas OrderedLinkedListImpl deben ser ordenadas
	@Test
	public void IsOrderedTest() {
		// todas las orderedLists deben estar ordenadas
		Assert.assertEquals(lA4B2.toString(),"(A A A A B B )");
		Assert.assertTrue(lA4B2.isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>("A","B")).isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>()).isOrdered());		
	}

	// Tests removeDuplicates en ordenada
	@Test
	public void testRemoveDuplicates() {
		Assert.assertEquals(lA4B2.removeDuplicates(),4); // 3 A + 1B repetidas
		Assert.assertEquals(lA4B2.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lA4B2.toString(), "(A B )");	
	}

	// Tests con personas
	@Test
	public void testInsertPersons() {
		OrderedLinkedListImpl<Person> lista=new OrderedLinkedListImpl<Person>(person1, person2, person3, person4);
		Assert.assertEquals(lista.toString(),"({01020304A, Sara, 16} {20304050A, Pedro, 18} {10203040A, Ana, 20} {30405060A, Pablo, 30} )");
		Assert.assertTrue(lista.isOrdered());
	}
	
	
	// AÑADIR MAS TESTS para el resto de casos especiales 

	@Test(expected = NullPointerException.class)
	public void addTest() {
		lA4B2.add("A");
		Assert.assertEquals(lA4B2.toString(),"(A A A A A B B )");
		lA4B2.add("d");
		Assert.assertEquals(lA4B2.toString(),"(A A A A A B B d )");
		lA4B2.add("C");
		Assert.assertEquals(lA4B2.toString(),"(A A A A A B B C d )");
		lA4B2.add(null);
	}
	
	@Test
	public void removeDuplicatesTest() {
		OrderedLinkedListImpl<String> vacio;
		vacio = new OrderedLinkedListImpl<>();
		
		Assert.assertEquals(vacio.removeDuplicates(),0);
		
		lA4B2.add("A");
		Assert.assertEquals(lA4B2.toString(),"(A A A A A B B )");
		lA4B2.add("C");
		Assert.assertEquals(lA4B2.toString(),"(A A A A A B B C )");
		
		Assert.assertEquals(lA4B2.removeDuplicates(),5);
		
		Assert.assertEquals(lA4B2.toString(),"(A B C )");
	}
	
	@Test(expected=NullPointerException.class)
	   public void testContains() {
		   Assert.assertFalse(lA4B2.contains("C"));
		   Assert.assertTrue(lA4B2.contains("A"));
		   Assert.assertTrue(lA4B2.contains("B"));
		   Assert.assertFalse(lA4B2.contains("X"));
		   lA4B2.contains(null);
	   }
	   
	   @Test(expected=NullPointerException.class)
	   public void testCount() {
		   
		   OrderedLinkedListImpl<String> lista=new OrderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		   
		   Assert.assertEquals(lista.count("A"), 3);
		   Assert.assertEquals(lista.count("B"), 2);
		   Assert.assertEquals(lista.count("C"), 2);
		   Assert.assertEquals(lA4B2.count("A"), 4);
		   Assert.assertEquals(lA4B2.count("B"), 2);

		   lista.count(null);
		   
	   }
	   
	   
	   @Test(expected=EmptyCollectionException.class)
	   public void testGetLast() throws EmptyCollectionException {
		   
		   OrderedLinkedListImpl<String> vacia=new OrderedLinkedListImpl<String>(); 
		   OrderedLinkedListImpl<String> lista=new OrderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		   OrderedLinkedListImpl<String> lista1=new OrderedLinkedListImpl<String>("B"); 


		   Assert.assertEquals(lA4B2.getLast(), "B");
		   Assert.assertEquals(lista.getLast(), "C");
		   Assert.assertEquals(lista1.getLast(), "B");
		   vacia.getLast();

	   }
	   
	   @Test
	   public void testIsOrdered() {
		   
		   OrderedLinkedListImpl<String> lista=new OrderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		   
		   Assert.assertTrue(lA4B2.isOrdered());
		   Assert.assertTrue(listaConElems.isOrdered());
		   
	   }
	   
	   @Test(expected=NullPointerException.class)
	   public void testRemove1() throws EmptyCollectionException {
		   
		   Assert.assertEquals(lA4B2.remove("A"), "A");
		   Assert.assertEquals(lA4B2.remove("A"), "A");
		   Assert.assertEquals(lA4B2.remove("B"), "B");
		   Assert.assertEquals(lA4B2.toString(), "(A A B )");
		   lA4B2.remove(null);

	   }
	   
	   @Test(expected=EmptyCollectionException.class)
	   public void testRemove2() throws EmptyCollectionException {
		   OrderedLinkedListImpl<String> vacia=new OrderedLinkedListImpl<String>(); 
		   vacia.remove("A");
	   }
	   
	   @Test(expected=NoSuchElementException.class)
	   public void testRemove3() throws EmptyCollectionException {
		   lA4B2.remove("D");
		   
	   }
	   
	   @Test(expected=NullPointerException.class)
	   public void testRemoveLast1() throws EmptyCollectionException {
		   
		   lA4B2.removeLast(null);

	   }
	   
	   @Test(expected=EmptyCollectionException.class)
	   public void testRemoveLast2() throws EmptyCollectionException {
		   
		   OrderedLinkedListImpl<String> vacia=new OrderedLinkedListImpl<String>(); 
		   vacia.removeLast("A");

	   }
	   
	   @Test(expected=NoSuchElementException.class)
	   public void testRemoveLast3() throws EmptyCollectionException {
		   
		   lA4B2.removeLast("D");

	   }
	   
	   @Test
	   public void testRemoveLast4() throws EmptyCollectionException {
		   
		   OrderedLinkedListImpl<String> lista;
		   lista = new OrderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 

		   
		   Assert.assertEquals(lA4B2.removeLast("A"), "A");
		   Assert.assertEquals(lA4B2.toString(), "(A A A B B )");
		   Assert.assertEquals(lA4B2.removeLast("B"), "B");
		   Assert.assertEquals(lA4B2.toString(), "(A A A B )");
		   
		   Assert.assertEquals(lista.removeLast("A"), "A");
		   Assert.assertEquals(lista.removeLast("A"), "A");
		   Assert.assertEquals(lista.removeLast("A"), "A");
		   Assert.assertEquals(lista.removeLast("B"), "B");
		   Assert.assertEquals(lista.toString(), "(B C C )");

		   




	   }
	   
	   @Test
	   public void testSize() {
		   Assert.assertEquals(lA4B2.size(), 6);
		   Assert.assertEquals(listaConElems.size(), 6);

	   }
	   
	   @Test(expected=EmptyCollectionException.class)
	   public void testGetFirst() throws EmptyCollectionException {
		   Assert.assertEquals(lA4B2.getFirst(), "A");
		   Assert.assertEquals(lA4B2.getFirst(), "A");
		   Assert.assertEquals(lA4B2.getFirst(), "A");
		   Assert.assertEquals(lA4B2.toString(), "(A A A A B B )");

		   OrderedLinkedListImpl<String> vacia=new OrderedLinkedListImpl<String>(); 
		   
		   vacia.getFirst();
	   }

	   @Test(expected=IllegalArgumentException.class)
	   public void toStringFromUntilTest() {
		   Assert.assertEquals(lA4B2.toStringFromUntil(1,3), "(A A A )");
		   Assert.assertEquals(lA4B2.toStringFromUntil(5,10), "(B B )");
		   Assert.assertEquals(lA4B2.toStringFromUntil(4,6), "(A B B )");

		   lA4B2.toStringFromUntil(-1, 3);
	   }
	   
	   @Test(expected=UnsupportedOperationException.class)
	   public void iteradorTest() {
		   Iterator<String> iter = lA4B2.iterator();
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "A");
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "A");
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "A");
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "A");
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "B");
		   Assert.assertTrue(iter.hasNext());
		   Assert.assertEquals(iter.next().toString(), "B");
		   Assert.assertFalse(iter.hasNext());

		   iter.remove();
	   }
	
	
	
}
