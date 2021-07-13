public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int front;
    private int rear;
    private int capacity = 8;

    /*Invariants:
    1.the size is must be determined.
    2.the first item's index is always (front+1)%8.Front always points to
     the front of the current's first item.
    3.the current last item's index is always (rear)%8
     */
    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        front = -1;
        rear = -1;
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        int i = 0;
        while (i < size) {
            int index = (front + i + 1) % size;
            a[i] = array[index];
            i++;
        }
        array = a;
        capacity = newCapacity;
        front = -1;
        rear = size - 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (front == rear && size == array.length) {
            resize(size * 2);
        }
        array[(front + capacity) % capacity] = item;
        front = (front + capacity) % capacity - 1;
        size++;
    }

    public void addLast(T item) {
        if (front == rear && size == capacity) {
            resize(size * 2);
        }
        array[(rear + 1) % 8] = item;
        rear = (rear + 1) % 8;
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int i, j;
        if (front < rear) {
            i = front;
            while (i < rear) {
                int index = (i + 1) % capacity;
                System.out.print(array[index] + " ");
                i++;
            }
        } else if (front > rear) {
            i = front;
            while (i + 1 < capacity) {
                System.out.print(array[i + 1] + " ");
                i++;
            }
            j = 0;
            while (j <= rear) {
                System.out.print(array[j] + " ");
                j++;
            }
        }

    }

    private boolean badUse() {
        double ratio = size / (double) capacity;
        if (ratio < 0.25 && capacity > 16) {
            return true;
        }
        return false;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T removed = array[front + 1];
        array[front + 1] = null;
        front = (front + 1) % capacity;
        if (badUse()) {
            resize(capacity / 2);
        }
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = array[rear];
        array[rear] = null;
        rear = (rear - 1 + capacity) % capacity;
        if (badUse()) {
            resize(capacity / 2);
        }
        size--;
        return removed;
    }

    public T get(int index) {
        int real = (front + 1 + index) % capacity;
        if (array[real] == null) {
            return null;
        }
        return array[real];
    }


    public static void main(String[] args) {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        //a1.addFirst("first added");
        a1.addLast("0");
        //a1.addFirst("third added");
        // a1.addFirst("kkkkkk");
        a1.addLast("1");
        a1.addFirst("3");
        System.out.print(a1.get(0));
        System.out.print(a1.get(1));
        System.out.print(a1.get(2));
        a1.printDeque();
        a1.removeFirst();
        a1.removeLast();
        a1.printDeque();
    }
}
