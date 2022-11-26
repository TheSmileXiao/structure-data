package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;


public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {
 
	// Atributos
	private QueueWithRepNode<T> front;
	int count;
	
	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;
		
		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
		
	}
	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
		
		private int count;
		private QueueWithRepNode<T> current;
		
       	
		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			
			this.count = 1;
			this.current = nodo;
			
		}
		
		@Override
		public boolean hasNext() {
			
			return (this.current != null);
			
		}

		@Override
		public T next() {
			
			T nextElement;
			
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}else {
				nextElement = this.current.elem;
				
				if(this.count < this.current.num) {
					this.count++;
				}else {
					this.count = 1;
					this.current = this.current.next;
				}
			}
			
			return nextElement;
		}

	}
	////// FIN ITERATOR
	
	public LinkedQueueWithRepImpl() {
		this.count = 0;
	}

	/////////////
	@Override
	public void add(T element) {
		if(element == null) {
			throw new NullPointerException("El elemento introducido debe ser distinto de null");
		}
		
		QueueWithRepNode<T> current, previous;
		current = this.front;
		previous = null;
		
		if(isEmpty()) {
			this.front = new QueueWithRepNode<T>(element, 1);
			this.count++;
		}
		else {
			if(!contains(element)) {
				current.next = new QueueWithRepNode<T>(element, 1);
				this.count++;
			}
			else{
				for(int i=0; i<count; i++) {
					if(current.elem.equals(element)) {
						current.num++;
					}else {
						previous=current;
						current=current.next;
					}
				}
			}
		}
	}
	
	@Override
	public void add(T element, int times) {
		
		if(times < 1) {
			throw new IllegalArgumentException();
		}

		for(int i=0; i<times; i++) {
			this.add(element);
		}
		
	}


	@Override
	public void remove(T element, int times) {
		if(element == null) {
			throw new NullPointerException();
		}

		if(this.contains(element) || times == 0) {
			boolean found = false;
			QueueWithRepNode<T> previous, current;
			current = this.front;
			previous = null;
			
			for(int i = 0; i < this.count && !found; i++) {
				if(current.elem.equals(element)) {
					
					if(times < current.num && times >= 0) {
						current.num -= times;
					}else if(times == current.num) {
						if(i == 0) {
							this.front = current.next;
						}else {
							previous.next = current.next;
						}
						this.count--;
					}else {
						throw new IllegalArgumentException();
					}
					
					found = true;
					
				}else {
					previous = current;
					current = current.next;
				}
			}
			
		}else {
			throw new NoSuchElementException();
		}
		
	}

	
	@Override
	public int remove() throws EmptyCollectionException {
		if(this.isEmpty()) {
			throw new EmptyCollectionException("LinkedQueueWithRepImpl");
		}
		
		int num = 0;
		
		num += this.front.num;
		this.front = this.front.next;
		this.count--;
		
		return num;
	}

	@Override
	public boolean contains(T element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		boolean found = false;
		QueueWithRepNode<T> current;
		current = this.front;
		
		for(int i = 0; i < this.count && !found; i++) {
			if(current.elem.equals(element)) {
				found = true;
			}else {
				current = current.next;
			}
		}
		
		return found;
	}

	@Override
	public long size() {
		long size = 0;
		QueueWithRepNode<T> current;
		current = this.front;
		
		for(int i = 0; i < this.count; i++) {
			size += current.num;
			current = current.next;
		}
		
		return size;
	}

	@Override
	public boolean isEmpty() {

		return (count==0);
	}

	@Override
	public void clear() {
		//todo
		QueueWithRepNode<T> current=null;
		
		this.front=current;
		
		count=0;


	}

	@Override
	public int count(T element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		int salida = 0;
		
		if(contains(element)) {
			
			boolean found = false;
			QueueWithRepNode<T> current;
			current = this.front;
			
			for(int i = 0; i < this.count && !found; i++) {
				if(current.elem.equals(element)) {
					found = true;
					salida = current.num;
				}else {
					current = current.next;
				}
			}
		}
		
		return salida;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueWithRepIterator<T>(this.front);
	}


	@Override
	public String toString() {
		
		QueueWithRepNode<T> current;
		current = this.front;
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("(");
		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		for(int i = 0; i < this.count; i++) {
			for(int j = current.num; j > 0; j--) {
				buffer.append(current.elem.toString() + " ");
			}
			current = current.next;
		}
		buffer.append(")");
		
		return buffer.toString();
	}

}
