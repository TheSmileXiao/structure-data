package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	
	@Before
	public void setupBSTs() {
		
			
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
	    	}

	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
		@Test
		public void testTagDecendentsEjemplo() {
			ejemplo.tagDecendents();
			ejemplo.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",ejemplo.toString());
		}
		
		@Test
		public void testTagHeightEjemplo() {
			other.tagHeight();
			other.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",other.toString());
		}
		
		
		@Test
		public void testTagOnlySonEjemplo() {
		
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3,other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",other.toString());

		}
		
		@Test
		public void insertTest1() {
			
			BinarySearchTreeImpl<Integer> nuevo= new BinarySearchTreeImpl<Integer>();
			ArrayList<Integer> lista = new ArrayList<Integer>();
			lista.add(3);
			lista.add(6);
			lista.add(9);
			lista.add(7);
			lista.add(5);
			lista.add(3); // ya esta en la lista, no va a añadir 2 num de 3 en el arbol
			Assert.assertEquals(5,nuevo.insert(lista));
			Assert.assertEquals("{3, ∅, {6, {5, ∅, ∅}, {9, {7, ∅, ∅}, ∅}}}" , nuevo.toString());
			
			lista.add(1);
			lista.add(2);
			lista.add(null);
			Assert.assertEquals(0,nuevo.insert(lista)); // no añade ninguna por contener elemento null
			Assert.assertEquals("{3, ∅, {6, {5, ∅, ∅}, {9, {7, ∅, ∅}, ∅}}}" , nuevo.toString());
			
		}
		
		@Test
		public void insertTest2() {
			BinarySearchTreeImpl<Integer> nuevo= new BinarySearchTreeImpl<Integer>();
			Assert.assertEquals(6 , nuevo.insert(5,4,8,1,3,2,5) );
			Assert.assertEquals("{5, {4, {1, ∅, {3, {2, ∅, ∅}, ∅}}, ∅}, {8, ∅, ∅}}" , nuevo.toString());
			Assert.assertEquals(0 , nuevo.insert(5,4,8,1,3,null,2,5) );
			Assert.assertEquals("{5, {4, {1, ∅, {3, {2, ∅, ∅}, ∅}}, ∅}, {8, ∅, ∅}}" , nuevo.toString());
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void insertTest3() {
			BinarySearchTreeImpl<String> nuevo= new BinarySearchTreeImpl<String>();
			Assert.assertTrue(nuevo.insert("A"));
			Assert.assertTrue(nuevo.insert("B"));
			Assert.assertTrue(nuevo.insert("C"));
			Assert.assertFalse(nuevo.insert("C"));
			nuevo.insert((String) null);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void containsTes() {
			Assert.assertTrue(ejemplo.contains(2));
			Assert.assertTrue(ejemplo.contains(15));
			Assert.assertTrue(ejemplo.contains(30));
			Assert.assertFalse(ejemplo.contains(6));
			ejemplo.contains(null);
		}
		
		@Test(expected = NoSuchElementException.class)
		public void removeTest1() {
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
			ejemplo.remove(10,null);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
			ejemplo.remove(10,2);
			Assert.assertEquals(ejemplo.toString(), "{15, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}");
			ejemplo.remove(30,1); //no contiene el 1.
		}
		
		@Test(expected = NoSuchElementException.class)
		public void removeTest2() {
			ejemplo.insert(22,25);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, {22, ∅, {25, ∅, ∅}}, ∅}}}");
			ejemplo.remove(22);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, {25, ∅, ∅}, ∅}}}");
			ejemplo.remove(22);
		}
		
		@Test
		public void testTagDecendents() {
			BinarySearchTreeImpl<String> vacia= new BinarySearchTreeImpl<String>();
			vacia.tagDecendents();
			Assert.assertTrue(ejemplo.insert(45));
			ejemplo.tagDecendents();
			other.filterTags("decendents");
			Assert.assertEquals(
					"{10 [(decendents, 6)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 3)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 1)], ∅, {45 [(decendents, 0)], ∅, ∅}}}}"
					,ejemplo.toString());		
		
		}

		@Test
		public void iteratorTest() {
			Iterator<Integer> iter = ejemplo.iteratorWidth();
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 10,iter.next());
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 5,iter.next());
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 20,iter.next());
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 2,iter.next());
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 15,iter.next());
			Assert.assertTrue(iter.hasNext());
			Assert.assertEquals((Object) 30,iter.next());
			Assert.assertFalse(iter.hasNext());

		}
		
		@Test
		public void testTagOnlySon() {
		
		Assert.assertTrue(ejemplo.insert(45));
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {45, ∅, ∅}}}}",ejemplo.toString());

		Assert.assertEquals(2,ejemplo.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {45 [(onlySon, 2)], ∅, ∅}}}}",ejemplo.toString());

		
		
		}
		
		
		

	
	}


