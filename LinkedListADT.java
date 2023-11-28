public class LinkedListADT<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> current;
	
	public int size;

	public LinkedListADT() {
		head = null;
		current = null;
		size = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		if (current == null)
			return;
		current = current.next;
	}

	public void update(T val) {
		current.data = val;
		;
	}

	public T retrieve() {
		if (current == null)
			return null;
		return current.data;
	}

	public boolean insertSort(T val) {

		Node<T> tmp;
		if (isEmpty()) {
			current = head = new Node(val);
		}
		else {
			// if val alphabetically is before head
			if (head.data.compareTo(val) > 0) {
				tmp = new Node<T>(val);
				tmp.next = head;
				head = tmp;
			}
			// a loop to check when a val should alphabetically swap with a node.
			else {
				Node<T> prev = null;
				current = head;

				while ((current != null) && (current.data.compareTo(val) <= 0)) {
					prev = current;
					current = current.next;
				}
				tmp = new Node<T>(val);
				if (current != null) {
					tmp.next = current;
					;
					prev.next = tmp;
					;
					current = tmp;
				} else
					current = tmp;
				prev.next = tmp;
			}
		}
		size++;
		return true;
	}

	public T remove(T val) {

		if (search(val) == false)
			return null;

		T data = current.data;

		if (current == head) {
			head = head.next;
		} else {
			Node tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
			;
		}
		if (current.next == null)
			current = head;
		else
			current = current.next;
		size--;
		return data;
	}

	public boolean search(T val) {
		if (head == null)
			return false;

		Node<T> node = head;
		while ((node != null) && (node.data.compareTo(val) != 0))
			node = node.next;
		if ((node != null) && (node.data.compareTo(val) == 0)) {
			current = node;
			return true;
		}
		return false;
	}
	
}