/*public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int front;
    private int rear;
    private int capacity = 8;

    /*Invariants:
    1.the size is must be determined.
    2.the first item's index is always (front+1)%capacity.Front always points to
     the front of the current's first item.
    3.the current last item's index is always rear

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
*/
   /**
     * Adds an item of type T to the front of the deque.
     */
   /*
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
        array[(rear + 1) % capacity] = item;
        rear = (rear + 1) % capacity;
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
    }*/

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    /*
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
        if(index < 0 || index > size || isEmpty()){
            return null;
        }
        int real = (front + 1 + index) % capacity;
        return array[real];
    }*/

/*
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
}*/

/**
 * Deque implemented by array.
 */
public class ArrayDeque<T> {

    private T[] items;
    private int left;
    private int right;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        left = right = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        left = (left - 1 + capacity) % capacity;
        items[left] = item;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        items[right] = item;
        right = (right + 1 + capacity) % capacity;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return left == right;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return (right - left + capacity) % capacity;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (left < right) {
            for (int i = left; i < right; i++) {
                if (i == right - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.print(items[i] + " ");
            }
        } else if (left > right) {
            for (int i = left; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < right; i++) {
                if (i == right - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.print(items[i] + " ");
            }
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = items[left];
        left = (left + 1) % capacity;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        right = (right - 1 + capacity) % capacity;
        T res = items[right];
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) {
            return null;
        }
        if (left < right) {
            return items[index + left];
        } else if (left > right) {
            if (index + left < capacity) {
                return items[index + left];
            } else {
                return items[(index + left) % capacity];
            }
        }
        return null;
    }

    private boolean isFull() {
        return size() == capacity - 1;
    }

    private boolean isLowUsageRate() {
        return capacity >= 16 && size() / (double) capacity < 0.25;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        int size = size();
        if (left < right) {
            for (int i = left, j = 0; i < right && j < size; i++, j++) {
                newArray[j] = items[i];
            }
        } else if (left > right) {
            int j = 0;
            for (int i = left; j < capacity - left; i++, j++) {
                newArray[j] = items[i];
            }
            for (int i = 0; j < size; i++, j++) {
                newArray[j] = items[i];
            }
        }
        left = 0;
        right = size;
        items = newArray;
        capacity = newSize;
    }
}
