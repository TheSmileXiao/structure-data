package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * Árbol binario de búsqueda (binary search tree, BST).
 * 
 * El código fuente está en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API debería ser el símbolo de conjunto vacío: ∅
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no está bien configurado en Eclipse para
 * usar esa codificación de caracteres.
 *
 * En el toString() que está ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un árbol vacío se representa como "∅". Un árbol no vacío
 * 		como "{(información raíz), sub-árbol 1, sub-árbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, ∅, ∅}, ∅} es un árbol binario con 
 * 		raíz "A" y un único sub-árbol, a su izquierda, con raíz "B".
 * 
 * El método render() también representa un árbol, pero con otro
 * formato; por ejemplo, un árbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  ∅
 * |  |  ∅
 * |  S
 * |  |  ∅
 * |  |  ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar información extra. Si es el caso, tanto toString() como
 * render() mostrarán los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> sería muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sólo con exactamente
 * T sino también con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la información en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

   BinarySearchTreeImpl<T> father;  //referencia a su nodo padre)

	/**
	 * Devuelve el árbol binario de búsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquí se sabe que es además de búsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el árbol binario de búsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Árbol BST vacío
	 */
	public BinarySearchTreeImpl() {
		// TODO HACER QUE THIS SEA EL NODO VACÍO
		this.father = null;
		this.setContent(null);
		this.setLeftBST(null);
		this.setRightBST(null);
	}
	
	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		// TODO HACER QUE THIS SEA EL NODO VACÍO, asignando como padre el parámetro recibido
		this.setContent(null);
		this.father = father;
	}


	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);
	}
	
	/**
	 * Inserta los elementos de una colección en el árbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya están no los inserta)
	 */
	public int insert(Collection<T> elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		//TODO Implementar el método
		
		for(T e : elements) {
			if(e == null)
				return 0;	
		}
		
		int n = 0;
		
		for(T e : elements) {
			if(this.insert(e)) {
				this.insert(e);
				n++;
			}
		}
		return n;
		
	}

	/**
	 * Inserta los elementos de un array en el árbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya están no los inserta)
	 */
	public int insert(T ... elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
	    // TODO Implementar el método
		
		for(T e : elements) {
			if(e == null)
				return 0;	
		}
		
		int n = 0;
		
		for(T e : elements) {
			if(this.insert(e)) {
				this.insert(e);
				n++;
			}
		}
		return n;
		
	}
	
	/**
	 * Inserta (como hoja) un nuevo elemento en el árbol de búsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null si es la raíz)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepción: IllegalArgumentException 
	 *  Si el elemento ya existe en el árbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null           
	 */
	public boolean insert(T element) {
    //	TODO Implementar el método
		
		if(element == null)
			throw new IllegalArgumentException("No se acepta elemento null");
		if(this.contains(element))
			return false;
			
		if(this.isEmpty()) {
			setContent(element);
			setLeftBST(emptyBST(this));
			setRightBST(emptyBST(this));
		}else {
			if(element.compareTo(this.getContent()) <0) 
				this.getLeftBST().insert(element);
			else
				this.getRightBST().insert(element);
		}
		
		return true;
	}
	
	/**
	 * Busca el elemento en el árbol.
	 * 
	 * No se permiten elementos null. 
	 * 
	 * @param element   valor a buscar.
	 * @return true si el elemento está en el árbol, false en caso contrario          
	 */
	public boolean contains(T element) {
		// TODO Implementar el método
		
		if(element == null)
			throw new IllegalArgumentException("No se acepta elemento null");
		
		if(this.isEmpty())
			return false;
		else {

			if(element.compareTo(this.getContent()) < 0 ) 
				return this.getLeftBST().contains(element);
			else if(element.compareTo(this.getContent()) > 0 ) 
				return this.getRightBST().contains(element);
			else
				return true;

		}
		
	}
	
	/**
	 * Elimina los valores en un array del árbol.
	 * O todos o ninguno; si alguno es 'null'o no lo contiene el árbol, no se eliminará ningún elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no está en el árbol           
	 */
	public void remove(T ... elements) {
	    // TODO Implementar el método
		
		for (T e : elements) 
			if (e == null)
				return;
		
		for(T e : elements) {
			if(!contains(e))
				throw new NoSuchElementException("Alguno de los elementos introducido no estaria en el arbol");
		}
		
		
		for(T e : elements) {
			this.remove(e);
		}
		
	}
	
	/**
	 * Elimina un elemento del árbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por
	 *  el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void remove(T element) {
		// TODO Implementar el método
		

		if(!contains(element))
			throw new NoSuchElementException("Elemento introducido no estaria en el arbol");

		if(element.compareTo(this.getContent()) < 0) {
			this.getLeftBST().remove(element);
		}
		else if(element.compareTo(this.getContent()) > 0) {
			this.getRightBST().remove(element);
		}
		else { //El caso de que es el elemento encontrado.
			
			BinarySearchTreeImpl<T> aux;
			
			if(this.isLeaf()) {  //Es una hoja
				this.setContent(null);
				this.setLeftBST(null);
				this.setRightBST(null);
			}
			
			else if(this.getLeftBST().isEmpty() || this.getRightBST().isEmpty()) {
				//Es el caso de que tiene un solo hijo
				
				if(!this.getLeftBST().isEmpty()) // Tiene hijo IZ
					aux = this.getLeftBST();
				else  //tiene hijo DR
					aux = this.getRightBST();
	
				this.setContent(aux.content);
				this.setLeftBST(aux.getLeftBST());
				this.setRightBST(aux.getRightBST());
				
			}else {
				//Cuando tiene 2 hijos 
				aux = this.getRightBST();
				
				while(!aux.getLeftBST().isEmpty())  //  aux -> menor de los mayores
					aux = aux.getLeftBST();
				
				this.setContent(aux.getContent());
				this.getRightBST().remove(aux.getContent());
				}
		}
		
	}	
	
	
	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la altura del nodo.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
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
     * 
	 * 
	 * el árbol quedaría etiquetado:
	 * 
	 *   {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅},
	 *               {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}
	 * 
	 */
	public void tagHeight() {
	// TODO implementar el método
				
		this.setTag("height", 1);
		this.tagHeightRec();
		
	}
	
	private void tagHeightRec() {		
		//preorden
		int nivel = (1 + (int) this.getTag("height"));
		
		BinarySearchTreeImpl<T> left = this.getLeftBST();
		BinarySearchTreeImpl<T> right = this.getRightBST();
		
		if(!left.isEmpty()) {
			left.setTag("height", nivel);
			left.tagHeightRec();
		}
		
		if(!right.isEmpty()) {
			right.setTag("height", nivel);
			right.tagHeightRec();
		}
		
	}
	
	
	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al número de descendientes que tiene en este árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
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
     * 
	 * 
	 * el árbol quedaría etiquetado:
	 * 
	 *  {10 [(decendents, 5)], 
	 *       {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, 
	 *       {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
	   // TODO Implementar el método
		//postOrden
				
		if(!isEmpty()) {
			BinarySearchTreeImpl<T> left = this.getLeftBST();
			BinarySearchTreeImpl<T> right = this.getRightBST();

			if(!left.isEmpty()) 
				left.tagDecendents();
			
			if(!right.isEmpty())
				right.tagDecendents();
			
			if(this.isLeaf()) {
				this.setTag("decendents",0);
			}else {
				int n = 0;
				
				if(left.getTag("decendents") != null)
					n = 1 + (int) left.getTag("decendents");
				
				if(right.getTag("decendents") != null)
					n = n + 1 + (int) right.getTag("decendents");
				
				this.setTag("decendents", n);
			}
			
			
		}
	}
	
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol por niveles según 
         * el recorrido en anchura
	 * 
	 * Por ejemplo, con el árbol
	 * 
	 * 		{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvería el iterador que recorrería los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		//	TODO Implementar método
		// puede implementarse creando una lista con el recorrido en anchura de los elementos del árbol y devolver el iterador de dicha lista
		
		ArrayList<BinarySearchTreeImpl<T>> cola = new ArrayList<BinarySearchTreeImpl<T>>();
		ArrayList<T> lista = new ArrayList<T>();
		
		cola.add(this);
				
		while(!cola.isEmpty()) {
			
			BinarySearchTreeImpl<T> aux = cola.remove(0);		
			
			if(!aux.isEmpty()) {
				lista.add(aux.getContent());
				cola.add(aux.getLeftBST());
				cola.add(aux.getRightBST());
			}
		}
		
		return lista.iterator();
		
	}	

	
	

	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Calcula y devuelve el número de nodos que son hijos únicos 
	 *  y etiqueta cada nodo que sea hijo único (no tenga hermano hijo del mismo padre) 
	 *   con la etiqueta "onlySon" y el valor correspondiente a su posición según el 
	 *   recorrido inorden en este árbol. 
	 *   
	 *   La raíz no se considera hijo único.
	 * 
	 * Por ejemplo, sea un árbol "A", que tiene 3 hijos únicos, los va etiquetando según 
	 * su recorrido en inorden. 
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
     *
	 * el árbol quedaría etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, 
	 *      {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}
	 * 
	 */
	public int tagOnlySonInorder() {
		// TODO Implementar el método
		
		int[] i = new int [1];

		tagOnlySonInorderRec(i);
		
		return i[0];
	}
	
	private void tagOnlySonInorderRec(int[] i) {
		
		BinarySearchTreeImpl<T> left = this.getLeftBST();
		BinarySearchTreeImpl<T> right = this.getRightBST();

		if(!left.isEmpty()) 
			left.tagOnlySonInorderRec(i);
		
		if(this.father != null) //no se considera la raiz como hijo unico
			if( (this.father.getLeftBST().isEmpty() ) || (this.father.getRightBST().isEmpty()) ) {
				i[0]++;
				this.setTag("onlySon", i[0]);
			}
		if(!right.isEmpty())
			right.tagOnlySonInorderRec(i);
	}
}

