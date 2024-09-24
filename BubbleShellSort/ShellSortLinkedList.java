import java.util.*;

public class ShellSortLinkedList {
	private Node first = null, last;

	private int count; // size of the linked list

	private class Node {
		int key;
		Node next;


		Node(int key) {
			this.key = key;
		}

	}

	public int size() {
		return count;
	}

	public void put(int key) {
		Node x = new Node(key);
		Node oldlast = last;
		last = x;
		
		if (first == null)
			first = last;
		
		else
			oldlast.next = last;
		
		count++;
	}


	private void swap(Node beforea, Node a, Node beforeb, Node b) {
		Node aux = a.next;
		a.next = b.next;

		if (aux != b) {
			b.next = aux;
			beforeb.next = a;
		} else {
			b.next = a;
		}

		if (beforea != null)
			beforea.next = b;

		if (a == first)
			first = b;
		else if (b == last)
			last = a;
	}

	// Sorts the linked list recursively, placing the node with the highest key at the end of the list and decrementing the length value.
// Repeats the process until the length becomes 0.
	private void bubblesort(int length, long[] a) {
		if (length <= 0) // When we can no longer decrement the length value, the list is sorted,
			return;      // so if length is 0, return and end the recursion.

		Node x = first, beforex = null, afterx = null;
		boolean sorted = true; // Boolean that indicates if the list is sorted or not

		// Iterate through the linked list
		for (int i = 0; i < length; i++) {
			a[0]++; // Increment the number of comparisons

			// If the key of node x is greater than the key of the next node, swap the nodes
			if (x.key > x.next.key) {
				a[1]++;
				afterx = x.next; // Store the node following x
				swap(beforex, x, x, afterx); // Call the swap function
				// When we perform the swap, we don't need to increment x because x will be in the x.next position after the swap.

				beforex = afterx; // beforex now contains the node before x, which after the swap is the node afterx

				sorted = false; // If there is a swap, it means the list is not sorted
			} else { // If the nodes are already sorted, no swap is necessary
				// Increment beforex and x
				beforex = x;
				x = x.next;
				sorted = true;
			}
		}
		--length; // Decrement the length

		// If the list is not sorted and length is greater than 0, we call the bubblesort recursively until we get a sorted list
		if (!sorted && length > 0) {
			bubblesort(length, a); // Recursively call the function with the new value of length
		}
	}


	private void bubbleHsort(int h, long[] a) {
		Node x = first, beforex = null, beforey = null, y = x;

		// Traverse h nodes in the list to place y h positions ahead of x
		for (int i = h; i > 0; i--) {
			beforey = y;
			y = y.next;
		}

		// Iterate through the list, comparing nodes that are h positions apart
		for (int i = 0; i < size() - h; i++) {
			a[0]++; // Increment the number of comparisons

			if (x.key > y.key) {
				swap(beforex, x, beforey, y); // Swap nodes x and y
				a[1]++; // Increment the number of swaps
				// Update the positions of x and y to reflect the new positions after the swap
				Node aux = x;
				x = y;
				y = aux;
				if (i >= h) {
					x = keepInOrder(i, h, x, a); // x holds the return value of the keepInOrder function

					// Compare x and y again to ensure they remain in order
					if (x.key > y.key) {
						swap(beforex, x, beforey, y);
						a[1]++;
						aux = x;
						x = y;
						y = aux;
					}
				}
			}
			// Increment beforex, x, beforey, and y to move to the next nodes in the list
			beforex = x;
			x = x.next;
			beforey = y;
			y = y.next;
		}
	}


	/* The function compares the stop node with the node that is h positions to the left.
 - If the key of that node is smaller than the key of stop, it means the list is h-sorted up to stop.
 - Otherwise, the function is called recursively using the node h positions to the left of stop as the new stop value. */


	private Node keepInOrder(int i, int h, Node stop, long[] a) {
		Node x = first, beforex = null, y = null, beforey = null, x0;

		// Move `x` to the position that is `i % h` steps from the start
		for (int j = 0; j < (i % h); j++) {
			beforex = x;
			x = x.next;
		}

		x0 = x; // Save the initial position of `x` in `x0`
		y = x;

		// Move `y` `h` positions ahead of `x`
		for (int j = 0; j < h; j++) {
			beforey = y;
			y = y.next;
		}

		// Increment `x` and `y` until `y` reaches `stop`
		while (y != stop) {
			beforex = x;
			x = x.next;
			beforey = y;
			y = y.next;
		}

		a[0]++; // Increment comparison counter
		if (x.key > y.key) { // If `x`'s key is greater than `y`'s key
			a[1]++; // Increment swap counter
			swap(beforex, x, beforey, y); // Swap nodes `x` and `y`

			// Update `x` and `y` positions after swap
			Node aux = x;
			x = y;
			y = aux;

			// If `y` is not equal to the initial position `x0`
			// It means there are more positions to check, so call the function recursively
			if (y != x0) {
				keepInOrder(i, h, x, a); // Call function recursively using `x` as the new `stop`
			}
		}
		return y; // Return `y` because `x` and `y` are now ordered
	}

	// public bubblesort
	public void bubblesort() {
		long[] a = new long[2];
		bubblesort(size() - 1, a);
	}

	public long[] shellSort() {
		// Create an array with 2 positions to store the comparison and swap counters
		long[] a = new long[2]; // cmp = a[0], exch = a[1];
		long cmp = 0, exch = 0; // Variables to hold the total number of comparisons and swaps

		int N = size(); // Variable N contains the number of elements in the linked list

		int h = 1; // Initialize the value of h to 1
		while (h < N / 3)
			h = 3 * h + 1; // Increment h while it is less than N/3 (using Knuth's increment sequence)

		// While h is greater than 1, use the bubbleHsort function to sort the list h positions apart,
		// and keep decreasing h until it reaches 1
		while (h > 1) {
			// Reset the array positions for comparisons and swaps to 0
			a[0] = 0;
			a[1] = 0;
			bubbleHsort(h, a); // Invoke the bubbleHsort function

			// Sum the comparison and swap counts from the multiple invocations of the bubbleHsort function
			cmp += a[0];
			exch += a[1];

			// Print the number of comparisons and swaps for each h value
			System.out.printf("\t %d\t %d\t\t%d\t\t\n", h, a[0], a[1]);

			// For lists with up to 20 elements, print the h-sorted list
			if (N <= 20)
				printList();
			h /= 3; // Decrease h
		}

		// If h is 1, invoke the bubblesort function
		if (h == 1) {
			// Reset the array positions to 0 to get the comparison and swap counts only for bubblesort
			a[0] = 0;
			a[1] = 0;
			bubblesort(N - 1, a);

			// Add the comparison and swap counts from bubblesort to the total counts
			cmp += a[0];
			exch += a[1];

			// Print the number of comparisons and swaps for h = 1 (bubblesort)
			System.out.printf("\t %d\t %d\t\t%d\t\t\n", h, a[0], a[1]);

			// For lists with up to 20 elements, print the sorted list
			if (N <= 20)
				printList();
		}
		// Assign the total number of comparisons and swaps to the array a
		a[0] = cmp;
		a[1] = exch;
		return a; // Return the array containing the total number of comparisons and swaps
	}


	public void printList() {
		Node x = first;
		
		while (x != null) {
			System.out.printf("%d ", x.key);
			x = x.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		ShellSortLinkedList list = new ShellSortLinkedList();
		
//		String ss[] = in.nextLine().split(" ");
//		for (int i = 0; i < ss.length; i++) {
//			list.put(Integer.parseInt(ss[i]));
//		}


		while (in.hasNextInt()) {
			list.put(in.nextInt());
		}
		
		int N = list.size();
		
		System.out.printf("\t h\t cmp \texch\t\t");
		if (N <= 20)
			list.printList();
		System.out.println("-----------------------------------------");

		long[] a = list.shellSort();

		System.out.println("-----------------------------------------");
		System.out.printf("  Total\t %d\t\t%d\t\t\n", a[0], a[1]);
		System.out.println("-----------------------------------------");
		System.out.printf("Time: \t");
		long end = System.currentTimeMillis();

		long elapsedTime = end - start;
		float sec = elapsedTime / 1000F;

		System.out.printf("%f seconds\n", sec);
		System.out.println("-----------------------------------------\t");
		in.close();
	}

}