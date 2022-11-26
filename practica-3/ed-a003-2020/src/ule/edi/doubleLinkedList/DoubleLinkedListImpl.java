package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ule.edi.exceptions.EmptyCollectionException;

import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {
	
	
	private DoubleNode<T> front;
	private DoubleNode<T> last;
	
	private class DoubleNode<T> {
		
		private T elem;
		private DoubleNode<T> next;
	    private DoubleNode<T> prev;
		
		public DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}
	}

	@SuppressWarnings("hiding")
	private class DobleLinkedListIterator<T> implements Iterator<T> {

		DoubleNode<T> nodo;
       
		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			
			this.nodo=nodo;
		}

		public boolean hasNext() {

			return (this.nodo != null);

		}

		@Override
		public T next() {
			
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}

			T nextElem = this.nodo.elem;
			this.nodo = this.nodo.next;

			return nextElem;
			
		}
	}
	
	private class DobleLinkedListIteratorReverse<T> implements Iterator<T> {

		DoubleNode<T> nodo;
		
		public DobleLinkedListIteratorReverse(DoubleNode<T> last) {
			this.nodo=last;
		}
		
		@Override
		public boolean hasNext() {
			return (this.nodo != null);
		}

		@Override
		public T next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			
			T nextElem = this.nodo.elem;
			this.nodo = this.nodo.prev;

			return nextElem;
		}
	}
	
	private class DobleLinkedListIteratorEven<T> implements Iterator<T> {
		
		private DoubleNode<T> nodo;
       	
		public DobleLinkedListIteratorEven(DoubleNode<T> front) {
			this.nodo = front.next;
		}
		
		@Override
		public boolean hasNext() {
			return (this.nodo!=null );
		}

		@Override
		public T next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			T nextElement = this.nodo.elem;
			if(this.nodo.next != null) 
				this.nodo=this.nodo.next.next;
			else 
				this.nodo=null;
			
			
			return nextElement;
		}
	}
	
	private class DobleLinkedListIteratorProgress<T> implements Iterator<T> {
		
		private DoubleNode<T> nodo;
		private int steps;
       	
		public DobleLinkedListIteratorProgress(DoubleNode<T> front) {
			this.nodo = front;
			this.steps = 0;
		}
		
		@Override
		public boolean hasNext() {
			return (this.nodo != null);
		}

		@Override
		public T next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			
			T nextElement = this.nodo.elem;
			this.steps++;
			
			for(int i=0; i<steps; i++) {
				if(this.nodo.next != null) 
					this.nodo=this.nodo.next;
				else 
					this.nodo=null;
			}
			
			return nextElement;
		}
	}
	
	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.insertLast(elem);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return this.front == null;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		DoubleNode<T> aux;
		
		while(this.last != null) {
			aux=this.last;
			last = aux.prev;
			aux.next = null;
			aux.elem = null;
			aux.prev = null;
		}
		
		this.front=null;
	}

	@Override
	public void insertFirst(T elem) {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		}
		
		DoubleNode<T> newFront = new DoubleNode<T>(elem);
		
		if(!this.isEmpty()) {
			newFront.next = this.front;
			this.front.prev = newFront;
		}else 
			this.last=newFront;

		this.front=newFront;
		
	}

	@Override
	public void insertLast(T elem) {
		// TODO Auto-generated method stub
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}

		if(!this.isEmpty()) {
			DoubleNode<T> newLast = new DoubleNode<T>(elem);
			
			newLast.prev = this.last;
			this.last.next = newLast;
			this.last = newLast;
			
		}else 
			this.insertFirst(elem);
	}


	@Override
	public T removeFirst() throws EmptyCollectionException{
		// TODO Auto-generated method stub
		
		if(this.isEmpty()) 
			throw new EmptyCollectionException("La lista esta vacia");
		
		T salida = this.front.elem;
		
		if(this.size() == 1) {
			
			this.front = null;
			this.last = null;
			
		}else {
			
			this.front.elem=null;
			this.front = this.front.next;
			this.front.prev.next=null;
			this.front.prev = null;
			
		}
		
		return salida;
	}


	@Override
	public T removeLast()  throws EmptyCollectionException{
		// TODO Auto-generated method stub
		
		if(this.isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacia");
		}
		
		T salida = this.last.elem;
		
		if(this.size() == 1) {
			this.removeFirst();
		}else {
			this.last.elem = null;
			this.last = this.last.prev;
			this.last.next.prev=null;
			this.last.next = null;
		}
		
		return salida;
	}


	@Override
	public void insertPos(T elem, int position) {
		// TODO Auto-generated method stub
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		if(position <= 0) {
			throw new IllegalArgumentException("La posicion tiene que ser mayor que 0");
		}
		
		if(position > this.size()) {
			this.insertLast(elem);
		}else if(position==1){
			this.insertFirst(elem);
		}else {
			
			DoubleNode<T> elemPrev = this.front;//position-1
			DoubleNode<T> elemNext = this.front.next;//position
			DoubleNode<T> newElement = new DoubleNode<T>(elem);
			
			for(int i=0; i<(position-2); i++) {//localiza pos-1 y pos.
				elemPrev = elemPrev.next;
				elemNext = elemNext.next;
			}
				elemPrev.next=newElement;
				elemNext.prev=newElement;
				newElement.prev=elemPrev;
				newElement.next=elemNext;
		}
		
	}


	@Override
	public void insertBefore(T elem, T target) {
		// TODO Auto-generated method stub
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		int pos=this.getPosFirst(target);
		
		this.insertPos(elem, pos);
		
	}


	@Override
	public T getElemPos(int position) {
		// TODO Auto-generated method stub
		
		if(position <= 0 || position > this.size()) {
			throw new IllegalArgumentException("La posicion tiene que ser mayor que 0 y menor que size()");
		}
		
		DoubleNode<T> aux = this.front;
		
		for(int i=1; i<position; i++) {
			aux=aux.next;
		}
		
		return aux.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		if(!this.contains(elem)) {
			throw new NoSuchElementException("El elemento introducido no esta en la lista");
		}
		
		int position = 0;
		DoubleNode<T> aux = this.front;
			
		for(int i=1; i<=this.size() && position == 0; i++) {
			if(aux.elem.equals(elem)) {
				position = i;
			}else {
				aux = aux.next;
			}
		}
		
		return position;

	}


	@Override
	public int getPosLast(T elem) {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		if(!this.contains(elem)) {
			throw new NoSuchElementException("El elemento introducido no esta en la lista");
		}
		
		int position = 0;
		DoubleNode<T> aux = this.last;
		
		for(int i=this.size(); position == 0; i--) {
			if(aux.elem.equals(elem)) {
				position = i;
			}else {
				aux = aux.prev;
			}
		}
		
		return position;
	}



	@Override
	public T removePos(int pos) {
		// TODO Auto-generated method stub
		if(pos<=0 || pos>this.size()) {
			throw new IllegalArgumentException("La posicion tiene que ser mayor que 0 y menor que size()");
		}
		
		T removeElem = this.front.elem;
		
		if(this.size() == 1) {
			this.clear();
		}else if(pos == 1) {
			this.front = this.front.next;
			this.front.prev.elem=null;
			this.front.prev.next=null;
			this.front.prev = null;
		}else if(pos == this.size()){
			removeElem = this.last.elem;
			this.last.elem = null;
			this.last=this.last.prev;
			this.last.next.prev = null;
			this.last.next = null;
			
		}else {
			DoubleNode<T> elemPrev = this.front;
			DoubleNode<T> aux = this.front.next;
			DoubleNode<T> elemNext = this.front.next.next;
			
			for(int i=0; i<(pos-2); i++) {
				elemPrev=elemPrev.next;
				aux=aux.next;
				elemNext=elemNext.next;
			}
			removeElem=aux.elem;
			aux.prev = null;
			aux.next = null;
			aux.elem = null;
			elemPrev.next = elemNext;
			elemNext.prev = elemPrev;
		}
		
		return removeElem;
		
	}


	@Override
	public int removeAll(T elem) {
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		if(!this.contains(elem)) {
			throw new NoSuchElementException("El elemento introducido no esta en la lista");
		}
		
		int i=1;
		int times = 0;
		DoubleNode<T> aux = this.front;
		
		while(aux!=null) {
			if(aux.elem.equals(elem)) {
				aux=aux.next;
				times++;
				this.removePos(i);
			}else {
				i++;
				aux=aux.next;
			}
		}
		
		
		return times;
	}


	@Override
	public boolean contains(T elem) {
		
		if(elem == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		boolean found = false;
		DoubleNode<T> aux = this.front;
		
		for(int i=1; i<=this.size() && !found; i++) {
			if(aux.elem.equals(elem)) {
				found = true;
			}else {
				aux = aux.next;
			}
		}
		
		return found;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size=0;
		
		DoubleNode<T> aux= this.front;
		
		while(aux!=null) {
			size++;
			aux=aux.next;
		}
		
		return size;
	}


	@Override
	public String toStringReverse() {
		return this.reverse().toString();
	}

	@Override
	public DoubleList<T> reverse() {
		
		DoubleList<T> reverseList = new DoubleLinkedListImpl<T>();
		
		Iterator<T> list = this.reverseIterator();
		
		while(list.hasNext()) {
			reverseList.insertLast( list.next() );
		}
		
		return reverseList;
		
	}	
	
	
	@Override
	public int maxRepeated() {
		
		int times = 0;
		DoubleNode<T> aux = this.front;
		DoubleNode<T> compared;
		
		for(int i=1; i<=this.size(); i++) {
			compared = aux.next;
			int provTimes = 1;
			
			for(int j=i+1; j<=this.size(); j++) {
				if(aux.elem.equals(compared.elem)) {
					provTimes++;
				}
				compared = compared.next;
			}
			
			if(provTimes > times) {
				times = provTimes;
			}
			
			aux = aux.next;
		}
		
		return times;
	}


	@Override
	public boolean isEquals(DoubleList<T> other) {
		// TODO Auto-generated method stub
		if(other == null) {
			throw new NullPointerException("La lista introducida no puede ser null");
		}
		
		boolean salida = false;
		boolean coinciden = true;
		
		if(this.size() == other.size()) {
			DoubleNode<T> aux = this.front;
			
			for(int i=1; i<=this.size() && coinciden; i++) {
				if(!aux.elem.equals(other.getElemPos(i))) {
					coinciden =false;
				}
				aux=aux.next;
			}
			
			if(coinciden) {
				salida=true;
			}
		}
	
		return salida;
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		if(other == null) {
			throw new NullPointerException("La lista introducida no puede ser null");
		}
		
		boolean salida = true;
		
		for(int i=1; i<=other.size() && salida; i++) {
			if(!this.contains(other.getElemPos(i))) {
				salida = false;
			}
		}
		
		return salida;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		if(other == null) {
			throw new NullPointerException("La lista introducida no puede ser null");
		}
		
		boolean salida = false;
		
		if(other.isEmpty()) {
			salida = true;
		}else {
			int coinciden = 1;
			boolean fin = false;
			DoubleNode<T> aux = this.front;
			
			while(!fin && !salida) {
				if(aux.elem.equals(other.getElemPos(coinciden))) {
					coinciden++;
				}else {
					coinciden = 1;
				}
			
				if(coinciden == other.size()+1) {
					salida = true;
				}
				
				if(aux == this.last) {
					fin = true;
				}else {
					aux = aux.next;
				}
			}
			
		}
		
		return salida;
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO Auto-generated method stub
		
		if(from <= 0 ) {
			throw new IllegalArgumentException("La posicion 'from' tiene que ser mayor que 0");
		}
		
		StringBuffer content = new StringBuffer("(");
		DoubleNode<T> aux = this.front;
		
		for(int i=1; i<=until && i<=this.size() ; i++) {
			if(i >= from) {
				content.append(aux.elem.toString() + " ");
			}
			aux = aux.next;
		}
		
		content.append(")");
		
		return content.toString();
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer content = new StringBuffer("(");
		DoubleNode<T> aux = this.front;
		
		while(aux != null) {
			content.append(aux.elem.toString() + " ");
			aux = aux.next;
		}
		content.append(")");
		
		return content.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<>(this.front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DobleLinkedListIteratorReverse<>(this.last);
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		return new DobleLinkedListIteratorEven<>(this.front);
	}

	

	@Override
	public Iterator<T> progressIterator() {
		return new DobleLinkedListIteratorProgress<>(this.front);
	}

}
