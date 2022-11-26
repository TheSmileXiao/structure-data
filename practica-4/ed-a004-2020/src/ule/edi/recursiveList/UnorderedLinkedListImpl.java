package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	Vacía
	}
	
	public UnorderedLinkedListImpl(T ... v) {
		//	Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
     //TODO
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		Node<T> aux = new Node<T>(element);
		
		aux.next = this.front;
		
		this.front = aux;
	}
	
	
	@Override
	public void addLast(T element) {
		// TODO RECURSIVO
		
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		Node<T> aux = new Node<T>(element);
		
		if(this.isEmpty())
			this.front = aux;
		else
			addLastRec(this.front,element);
	}	
	
	private void addLastRec(Node<T> lista,T element) {
		
		if(lista.next != null) 
			addLastRec(lista.next,element);
		else {
			Node<T> aux = new Node<T>(element);
			lista.next = aux;
		}

	}

	
	@Override
	public void addBefore(T element, T target) {
		// TODO RECURSIVO
		
		if(element == null)
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		
		if(!this.contains(target))
			throw new NoSuchElementException( target +" No esta en la lista");
			
		if(this.front.elem.equals(target))
			addFirst(element);
		else
			addBeforeRec(this.front,element,target);
		
	}
	
	private void addBeforeRec(Node<T> lista, T element, T target) {
		
		Node<T> actual = lista;
		Node<T> sig = lista.next;
		Node<T> aux = new Node<T>(element);

		if(sig.elem.equals(target)){
			lista.next = aux;
			aux.next = sig;
		}else {
			addBeforeRec(lista.next, element, target);
		}
	}

		
}
