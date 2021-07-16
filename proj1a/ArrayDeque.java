public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int front;
    private int rear;
    private int capacity = 8;

    /* Invariants:
    1.the size is must be determined.
    2.the first item's index is always (front +1 + capacity) % capacity. Front always points to
     the front of the current's first item.
    3.the current last item's index is always （rear + capacity) % capacity */

    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[capacity];
        front = -1;
        rear = -1;
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        int i = 0;
        while (i < size) {
            int index = (front + i + 1) % capacity;
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
        if (size == capacity) {
            resize(size * 2);
        }
        array[(front + capacity) % capacity] = item;
        front = (front + capacity - 1) % capacity ;
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        array[(rear + 1 + capacity) % capacity] = item;
        rear = (rear + 1 + capacity) % capacity;
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
        if (ratio < 0.25 && capacity >= 16) {
            return true;
        }
        return false;
    }

    public T removeFirst() {
        if (isEmpty()) {
            front = -1;
            rear = -1;
            return null;
        }
        size--;
        T removed = array[(front + 1) % capacity];
        array[(front + 1) % capacity] = null;
        front = (front + 1 + capacity) % capacity;
        if (badUse()) {
            resize(capacity / 2);
        }
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            front = -1;
            rear = -1;
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
        if(index < 0 || index > size || isEmpty()){
            return null;
        }
        int real = (front + 1 + index + capacity) % capacity;
        return array[real];
    }
}


