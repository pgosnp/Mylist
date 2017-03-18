
public class MyList extends AbsList {
	int pointer, index, listStart;
	int numOfData;

	public MyList(int size) {
		super(size);
		index = size;
		numOfData = 0;

		for (int i = 0; i < size; i++) {
			arr[i][0] = -1; // -1 represents data is empty
			arr[i][1] = -1; // -1 represents next is null
		}
		findEmpty(); // point the empty space into pointer
	}

	@Override
	public boolean append(int v) {
		boolean append = false;
		int position = listStart;
		findEmpty(); // point the empty space into pointer
		if (isEmpty()) {
			arr[pointer][0] = v;
			numOfData++;
			append = true;
		} else if (numOfData != 0) {
			for (int i = 0; i < index; i++) {
				if (arr[position][1] == -1) {
					arr[pointer][0] = v;
					numOfData++;
					arr[position][1] = pointer;
					append = true;
					break;
				}
				position = arr[position][1];
			}
		}
		return append;
	}

	@Override
	public boolean insert(int v, int p) {
		boolean insert = false;
		findEmpty(); // point the empty space into pointer
		int position = listStart;
		int beforePo = -1;
		if (p <= numOfData) {
			if (!isFull()) {
				if (p == 0) {
					arr[pointer][0] = v;
					if (!isEmpty()) {
						arr[pointer][1] = listStart;
					}
					listStart = pointer;
					insert = true;
					numOfData++;
				} else {
					// System.out.println("listStart: " + listStart);
					// System.out.println("First position: " + position);
					for (int i = 0; i < p; i++) {
						if (i == p - 1) {
							beforePo = position;
						}
						position = arr[position][1];
					}
					// System.out.println("beforePo: " + beforePo);
					// System.out.println("position: " + position);
					arr[pointer][0] = v;
					arr[pointer][1] = position;
					arr[beforePo][1] = pointer;
					numOfData++;
					insert = true;
				}
			}
		}
		return insert;
	}

	@Override
	public boolean delete(int v) {
		boolean delete = false;
		int position = listStart;
		int beforePo = -1;

		if (arr[listStart][0] == v) {
			arr[position][0] = -1;
			listStart = arr[position][1];
			arr[position][1] = -1;
			numOfData--;
			delete = true;
		}

		for (int i = 0; i < index; i++) {
			if (arr[position][1] != -1) {
				if (arr[position][0] != v) {
					beforePo = position;
					position = arr[position][1];
				} else {
					arr[position][0] = -1;
					numOfData--;
					arr[beforePo][1] = arr[position][1];
					arr[position][1] = -1;
					delete = true;
					break;
				}
			} else {
				if (arr[position][0] == v) {
					arr[position][0] = -1;
					numOfData--;
					arr[beforePo][1] = arr[position][1];
					arr[position][1] = -1;
					delete = true;
					break;
				}
			}
		}

		return delete;
	}

	@Override
	public int find(int v) {
		int position = listStart;
		int find = -1;
		for (int i = 0; i < index; i++) {
			if (arr[position][0] != v) {
				position = arr[position][1];
			} else {
				find = i;
				break;
			}
		}
		return find;
	}

	@Override
	public void traversal() {
		int position = listStart;
		if (isEmpty()) {
			System.out.println("The list is empty.");
		} else {
			for (int i = 0; i < index; i++) {
				System.out.print(arr[position][0] + " ");
				if (arr[position][1] != -1)
					position = arr[position][1];
				else {
					break;
				}
			}
		}
	}

	@Override
	boolean isEmpty() {
		return (numOfData == 0);
	}

	@Override
	boolean isFull() {
		return (numOfData == index);
	}

	@Override
	int length() {
		return numOfData;
	}

	public void findEmpty() {
		for (int i = 0; i < index; i++) {
			if (arr[i][0] == -1) {
				pointer = i;
				break;
			}
		}
	}
}
