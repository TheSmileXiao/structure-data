package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableException;

public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	protected  class Node<T> {

		T elem;
		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

	}
	
	private class IteratorImpl implements Iterator<T> {
     // TODO Implementar el iterador normal
		
		Node<T> nodo;
		
		public IteratorImpl(Node<T> first) {
			this.nodo = first;
		}
		
		
		@Override
		public boolean hasNext() {

		return this.nodo != null;
		}

		@Override
		public T next() {

			T nextElem = this.nodo.elem;
			this.nodo = this.nodo.next;

			return nextElem;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	// Ejemplos de ejercicios de recursividad
	//



	@Override
	public String toString() {
		// TODO RECURSIVO
		//	Construye y devuelve con el formato adecuado "(A B C )" 
		
		String cadena;
		
		cadena = "(";
		cadena = cadena + toStringRec(this.front);
		cadena = cadena + ")";
		
		return cadena;

	}
	
	private String toStringRec(Node<T> lista) {
		
		String li = "";
		
		if(lista != null) {
			li = lista.elem + " " + toStringRec(lista.next);
		}
		
		return li;
	}
	
	
	@Override
	public boolean contains(T target) {
		// TODO RECURSIVO
		
		if(target == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		if(this.isEmpty())
			return false;
		
		return containsRec(this.front,target);
	}
	
	private boolean containsRec(Node<T> aux, T target) {
		
		if(aux.elem.equals(target) ) //caso basico
			return true;
		else {
			if(aux.next != null)
				return containsRec(aux.next,target);
			else
				return false;	//no ha encontrado el elemento
		}

	}

	
  @Override
	public int count(T element) {
	// TODO RECURSIVO
	  
	  if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
	  
	  if(this.isEmpty())
		  return 0;
	  
	  return countRec(this.front,element);
		
	}
  
  private int countRec(Node<T> aux,T element) {
	  
	  if(aux.elem.equals(element) && aux.next == null) //caso basico
		  return 1;
	  
	  if(!(aux.elem.equals(element)) && aux.next == null) // caso basico
		  return 0;
	  
	  //si no cumple if anteriores -> aux.next != null
	  
	  if(aux.elem.equals(element))
		  return 1 + countRec(aux.next,element);
	  else
		  return countRec(aux.next,element);
	  
  }


	@Override
	public T getLast() throws EmptyCollectionException {
		// TODO RECURSIVO
		
		if(this.isEmpty())
			throw new EmptyCollectionException("La lista esta vaica");
		
		return 	getLastRec(this.front);
	}

	private T getLastRec(Node<T> aux) {
		
		if(aux.next != null)
			return getLastRec(aux.next);
		else
			return aux.elem;

	}
	
	@Override
	public boolean isOrdered() {
     // TODO RECURSIVO
		
		if(isEmpty())
			return true;
		
		try {
			return isOrderedRec(this.front);
		}catch(ClassCastException e) {
			throw new TypeIsNotComparableException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean isOrderedRec(Node<T> lista) {
		
		if(lista.next == null)
			return true;
		
		if( (((Comparable<T>) lista.elem).compareTo(lista.next.elem) > 0))
			return false;
		else
			return isOrderedRec(lista.next);
		
		
	}

	
	@Override
	public T remove(T element) throws EmptyCollectionException {
    // TODO RECURSIVO
		
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		if(this.isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		
		if(this.count(element) < 1)
			throw new NoSuchElementException("El elemento no esta en la lista");
		
		T result;
		if(this.front.elem.equals(element)) {
			result = this.front.elem;
			this.front = this.front.next;
			return result;
		}
		
		return removeRec(this.front,element);
	}
	
	private T removeRec(Node<T> aux,T element) { 
		
		T result;
		
		//por count >1 en el metodo remove aseguramos que el elemento esta en la lista.
		if(aux.next.elem.equals(element)) { //caso basico.
			result = aux.next.elem;
			aux.next = aux.next.next;
			return result;
		}
		
		return removeRec(aux.next, element);
		
	}




	@Override
	public T removeLast(T element) throws EmptyCollectionException {
     // TODO RECURSIVO
		
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		if(this.isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		
		if(this.count(element) < 1)
			throw new NoSuchElementException("El elemento no esta en la lista");
		
		T result;
		
		if(this.front.elem.equals(element) && (this.count(element) == 1) ) {
			result = this.front.elem;
			this.front = this.front.next;
			return result;
		}
			
		int num = this.count(element);
		if(this.front.elem.equals(element))
			num--;
		
		result = removeLastRec(this.front,element,num);
		
		return result;
	}
	
	private T removeLastRec(Node<T> lista,T element,int num) {
		
		if(lista.next.elem.equals(element))
			num--;
		
		if(num == 0) {
			
			T result;
			
			result = lista.next.elem;
			lista.next = lista.next.next;
			return result;
			
		}else
			return removeLastRec(lista.next,element,num);
		
	}


	@Override
	public boolean isEmpty() {
		// TODO 
		return this.front == null;
	}

	@Override
	public int size() {
		// TODO RECURSIVO
		return sizeRec(this.front);
	}
	
	private int sizeRec(Node<T> aux) {
		
		if(aux == null)	//si la lista esta vacia devuelve 0.
			return 0;
		if(aux.next == null) //caso basico
			return 1;
		else
			return 1 + sizeRec(aux.next);

	}

	@Override
	public T getFirst() throws EmptyCollectionException {
		
		if(this.isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
			
		return this.front.elem;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO RECURSIVO
		
		if(from <= 0 ) 
			throw new IllegalArgumentException("La posicion 'from' tiene que ser mayor que 0");

		if(from > this.size())
			return "()";
		
		String cadena;
		
		cadena = "(";
		cadena = cadena + toStringFromUntilRec(this.front,from,until,1);
		cadena = cadena + ")";
		
		return cadena;
	}
	
	private String toStringFromUntilRec(Node<T> lista,int from,int until, int pos) {
		
		String li = "";
		
		if(lista != null) { 
			
			if(pos >= from && pos <= until) 
				li = lista.elem + " " +toStringFromUntilRec(lista.next,from,until,++pos);
			else 
				return toStringFromUntilRec(lista.next,from,until,++pos);
		}
		
		return li;
	}


	@Override
	public String toStringReverse() {
		// TODO RECURSIVE
		
		String cadena;
		
		cadena = "(";
		cadena = cadena + toStringReverseRec(this.front);
		cadena = cadena + ")";
		
		return cadena;
	}
	
	private String toStringReverseRec(Node<T> lista) {
		
		String li = "";
		
		if(lista != null) {
			li = toStringReverseRec(lista.next)  + lista.elem + " ";
		}

		return li;
	}
	

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		// TODO RECURSIVE
		// Implementar teniendo en cuenta que la lista está desordenada
		
		if(isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		
		return removeDuplicatesRec(this.front);
	}
	
	public int removeDuplicatesRec(Node<T> lista) {
		
		if(lista.next == null) //caso basico
			return 0;	
		
		int num = this.count(lista.elem);
		
		if(num == 1)
			return removeDuplicatesRec(lista.next);
		else {
			this.removeLastRec(lista,lista.elem,1);  //elimina la segunda que tiene el mismo contenido que la lista.
			return 1 + removeDuplicatesRec(lista);
		}
	}

	
	@Override
	public Iterator<T> iterator() {
		// TODO 
		return new IteratorImpl(this.front);
	}


}