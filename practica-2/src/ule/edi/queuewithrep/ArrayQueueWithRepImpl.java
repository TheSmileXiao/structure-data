package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;


public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	ElemQueueWithRep<T>[] data;
    private int count;
    
	// Clase interna 
    
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		
		private int count;
		private int current;
		ElemQueueWithRep<T>[] cola;
		
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			
			this.cola=cola;
			this.count = count;
			this.current = 0; 
			
		}
		
		@Override
		public boolean hasNext() {
			
			return (current < count);
			
		}

		@Override
		public T next () throws NoSuchElementException
		{
			if (! hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				current ++;
				return (T) data[current-1];
			}
		} 
		

	}
	////// FIN ITERATOR
	
	
    // Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =   new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}
	
	
	 @SuppressWarnings("unchecked")
	 private void expandCapacity() {
		
			ElemQueueWithRep<T>[] nuevo= new ElemQueueWithRep[data.length*2];
			
			for(int i=0; i<data.length; i++) {
				nuevo[i]=data[i];
			}
			
			this.data=nuevo;
			// TODO
		}
	 		
			@SuppressWarnings("unchecked")
			@Override
			public void add(T element, int times) throws NullPointerException{
				
        
				if (times < 1) {
		            throw new IllegalArgumentException("El time debe ser mayor o igual que uno");
		        }
				
				for(int i=0; i<times; i++) {
					this.add(element);
				}
			}
			

			@SuppressWarnings("unchecked")
			@Override
			public void add(T element) throws NullPointerException{
				
				if (element == null) {
		            throw new NullPointerException("El elemento no puede ser null");
		        }
				
				if(this.count == this.data.length) {
					this.expandCapacity();
				}
				
				if(!(contains(element))){
					this.data[count] = new ElemQueueWithRep<T>(element, 1);
					count++;
				}else {
					for(int i = 0; i< this.count; i++) {
						if(this.data[i].elem.equals(element)) {
							this.data[i].num++;
						}
					}
				}
				
			}

			@Override
			public void remove(T element, int times){
				
				if (element == null) {
		            throw new NullPointerException("El elemento no puede ser null");
		        }
				
				if (times < 0) {
		            throw new IllegalArgumentException("El time debe ser mayor o igual que 0");
		        }
				
				if(!contains(element) && times!=0) {
					throw new NoSuchElementException("No se encuentra el elemento");
				}
				
				else{
				
					for(int i = 0; i< this.count; i++) {
						
						if(this.data[i].elem.equals(element)) {
							if(times<data[i].num) {
								this.data[i].num-=times;
							}else {
								throw new IllegalArgumentException("El time que has introducido es demasiado grande.");
							}
						}
					}	
				}
				 
			}
			
			@Override
			public int remove() throws EmptyCollectionException {
				
				if(isEmpty()) {
					throw new EmptyCollectionException("La cola esta vacia");
				}
				
				int salida= data[0].num;
				
				for(int i=0; i<count-1; i++) {
					data[i]=data[i+1];
				}
				
				data[count-1]=null;
				
				return salida;
				//todo
				
			}

			@Override
			public void clear() {
				// TODO 
				for(int i = 0; i< this.count; i++) {
					this.data[i] = null;
				 }
				
				this.count = 0;
			}
			

			@Override
			public boolean contains(T element){
				// TODO 
				boolean elementFound = false;
				
				if(element == null) {
					throw new NullPointerException("El elemento no puede ser null");
				}
				
				for(int i=0; i<this.count && !elementFound; i++) {
					if(this.data[i].elem.equals(element)) {
						elementFound = true;
					}
				}
				
				return elementFound;
			}

			@Override
			public boolean isEmpty() {
				
				return (count==0);
			}

			@Override
			public long size() {
				long num = 0;
				
				for(int i = 0; i < this.count; i++) {
					num += this.data[i].num;
				}
				
				return num;
				// TODO 
				
			}

			@Override
			public int count(T element) throws NullPointerException{
				
				if(element == null) {
					throw new NullPointerException("El elemento no puede ser null");
				}
				
				int num = 0;
				
				for(int i=0; i < this.count && num == 0; i++) {
					if(this.data[i].elem.equals(element)) {
						num = this.data[i].num;
					}
				}
				
				return num;
			}
				

			@Override
			public Iterator<T> iterator() {
				// TODO 
				return new ArrayQueueWithRepIterator<T>(this.data, this.count);
			}
			
			
			
			@Override
			public String toString() {
				
				final StringBuffer buffer = new StringBuffer();
				
				buffer.append("(");

				// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
				
				for(int i = 0; i < this.count; i++) {
					for(int j = this.data[i].num; j > 0; j--) {
						buffer.append(this.data[i].elem.toString() + " ");
					}
				}
				
				buffer.append(")");
				
				return buffer.toString();
			}

	
}
