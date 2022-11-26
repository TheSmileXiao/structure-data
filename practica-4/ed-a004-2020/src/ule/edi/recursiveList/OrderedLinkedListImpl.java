package ule.edi.recursiveList;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import ule.edi.exceptions.EmptyCollectionException;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends
		AbstractLinkedListImpl<T> implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}

	
	

	
	@Override
	public void add(T element) {
		//TODO RECURSIVO
		
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
	
		Node<T> newElem = new Node<T>(element);
		
		if(isEmpty()) 
			this.front = newElem;
		else if(this.front.elem.compareTo(element) > 0) {
			newElem.next = this.front;
			this.front = newElem;
		}else {
			addRec(this.front,element);
		}
		
		
	}
	
	private void addRec(Node<T> lista,T element) {
		
		Node<T> aux = new Node<T>(element);
		
		if(lista.next != null) {
			if(lista.next.elem.compareTo(element) > 0) {
				aux.next = lista.next;
				lista.next = aux;
			}else
				addRec(lista.next,element);
			}
		else {
			lista.next = aux;
		}
	}
	
	

	@Override
	public int removeDuplicates() {
  // TODO RECURSIVE 
  // Redefinir para listas ordenadas (los duplicados estarán consecutivos)
		
		if(isEmpty())
			return 0;
		else
			return removeDupRec(this.front);
		
	}
	
	private int removeDupRec(Node<T> lista) {
		
		if(lista.next == null)
			return 0;
		
		if(lista.elem.equals(lista.next.elem)) {
			lista.next=lista.next.next;
			return 1 + removeDupRec(lista);
		}else {
			return removeDupRec(lista.next);
		}
		
	}

}
