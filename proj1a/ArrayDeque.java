public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int front;
    private int rear;
    private int capacity = 8;
    /*Invariants:
    1.the size is must be determined.
    2.the first item's index is always (front+1)%8.Front always points to the front of the current's first item.
    3.the current last item's index is always (rear)%8
     */
    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        front = -1;
        rear = -1;
    }

    private void resize(int new_capacity) {
        T[] a = (T[]) new Object[new_capacity];
        System.arraycopy(array, 0, a, 0, size);
        array = a;
        capacity = array.length;
    }

    /** Adds an item of type T to the front of the deque. */
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
        rear  = (rear + 1) % 8;//the next last item's index
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

    public T removeFirst() {
        T removed = array[(front + 1) % 8];
        if (removed == null) {
            return null;
        } else {
            array[(front + 1) % 8] = null;
            front = (front + 1) % 8;
        }
        size--;
        return removed;
    }

    public T removeLast() {
        T removed = array[rear];
        if (removed == null) {
            return null;
        } else {
            array[rear] = null;
            rear = (rear - 1) % 8;
        }
        size--;
        return removed;
    }

    public T get(int index) {
        int real = (front + 1 + index) % 8;
        if (array[real] == null){
            return null;
        }
        return array[real];
    }


    public static void main(String[] args) {
        ArrayDeque<String > a1 = new ArrayDeque<>();
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
//        a1.removeFirst();
//        a1.removeLast();
    }
}