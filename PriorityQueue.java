import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PriorityQueue implemented as a Binary Heap backed by an array. Implements
 * QueueADT. Each entry in the PriorityQueue is of type PriorityQueueNode<E>.
 * First element is array[1]
 * 
 * @author CS367
 *
 * @param <E>
 */
public class PriorityQueue<NFLTeam> 
	{
	private final int CAPACITY = 16;

	// Number of elements in heap
	private int currentSize;
	//the root of the array is always at array[1]
	private final int ROOT = 1;
	//makes the percolating phase easier
	private int endOfArray;
	
	/**
	 * The heap array. First element is array[1]
	 */
	private NFLTeam[] array;
	
	/**
	 * Construct an empty PriorityQueue.
	 */
	public PriorityQueue()
		{
		currentSize = 0;
		this.endOfArray = 0;
		array = (NFLTeam[])new Array[CAPACITY];
		
		}

	/**
	 * Adds an item to this PriorityQueue. If array is full, double the array
	 * size.
	 * 
	 * @param item
	 *            object of type PriorityQueueItem<E>.
	 */
	
	public void enqueue(NFLTeam item)
		{
		//use this to determine whether the priority queue contains the item
		boolean foundMatchingItem = false;

		if (item == null){
			throw new IllegalArgumentException();
		}
		
		if(currentSize == 0){
			//if the PQ is empty, just add an item to the start
			array[ROOT] = item;
			currentSize++;
		}

		else{

			}	
			
		}

	/**
	 * Returns the number of items in this PriorityQueue.
	 * 
	 * @return the number of items in this PriorityQueue.
	 */
	public int size()
		{
		
		return this.currentSize;
		}


	/**
	 * Returns the largest item in the priority queue.
	 * 
	 * @return the smallest item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	
	public NFLTeam peek()
		{
			return array[ROOT];
		}

	/**
	 * Removes and returns the largest item in the priority queue. Switch last
	 * element with removed element, and percolate down.
	 * 
	 * @return the largest item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	
	public NFLTeam dequeue()
		{
		
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		// Remove first element
		NFLTeam tmp = array[ROOT];

		// Replace with last element in the array, percolate down
		array[ROOT] = array[currentSize];
		//set to null so there aren't duplicates or skipped values
		array[currentSize] = null;
		
		
		//reorganize the heap
		percolateDown(ROOT);
		//this.buildHeap();
		currentSize--;
		return tmp;
		}

	/**
	 * Heapify Establish heap order property from an arbitrary arrangement of
	 * items. ie, initial array that does not satisfy heap property. Runs in
	 * linear time.
	 */
	private void buildHeap()
		{
		for (int i = currentSize / 2;i > 0;i--)
			percolateDown(i);
		}

	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear()
		{
		
		if(this.isEmpty()){
			//if there are no elements, theres nothing to clear
			return;
			}
		//simply dequeue the whole array
		int size = currentSize;
		for(int i = 0; i < size; i++){
			this.dequeue();
			}
		}

	/**
	 * Internal method to percolate down in the heap. <a
	 * href="https://en.wikipedia.org/wiki/Binary_heap#Extract">Wiki</a>}
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole)
		{

		if (hole <= 0){ //not a viable item location
			return;
		}
		if(hole > currentSize || hole * 2 > array.length
				|| hole * 2 + 1 > array.length){ //base case
			//make sure that we are not trying to percolate past a valid 
			//array position
			return;
		}

		NFLTeam parent = array[hole];
		NFLTeam leftChild = array[hole * 2];
		NFLTeam rightChild = null;
		//takes care of some issues i had where running CourseData2 and 3 
		//threw index out of bounds exceptions
		if(leftChild != null){
			rightChild = array[hole * 2 + 1];
		}
		
		
		
		if(leftChild != null && rightChild != null){
			//if both children are actual items, then choose
			//whichever child is higher priority (default to left if equal)
			if(leftChild.compareTo(rightChild) > 0 
					|| leftChild.compareTo(rightChild) == 0){
				if(leftChild.compareTo(parent) > 0){
					//if child is higher priority than parent
					array[hole] = leftChild;
					array[hole * 2] = parent;
					//swap the values  
					
				}
				//keep percolating down the left child
				percolateDown(hole * 2);
			}
			else if(leftChild.compareTo(rightChild) < 0){
				//percolate to the right child
				if(rightChild.compareTo(parent) > 0){
					array[hole] = rightChild;
					array[hole * 2 + 1] = parent;
					//swap the values
					
				} 
				//keep percolating down the right child
				percolateDown(hole * 2 + 1);
			}
		}
		else if (rightChild == null && leftChild != null){
			//if there is no right child, just go to the left child
			if(leftChild.compareTo(parent) > 0){
				array[hole] = leftChild;
				array[hole * 2] = parent;
				//swap the values 
				}
			return;
			}
		
		}
	
	private void percolateUp(int hole){
		if(hole <= 1){//base case
			return;
		}
		NFLTeam child = (NFLTeam)array[hole];
		NFLTeam parent = (NFLTeam)array[hole / 2];
		
		if(child.compareTo(parent) > 0){
			//swap the values and keep percolating 
			array[hole / 2] = child;
			array[hole] = parent;
			percolateUp(hole / 2);
		}
		else{
			return;
		}
	}
	

	public boolean isEmpty()
		{
		return currentSize == 0;
		}
	
	}
	