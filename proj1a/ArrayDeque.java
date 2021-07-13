public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int front;
    private int rear;

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

    public void addFirst(T item) {
        if (front == -1 && array[7] == null) {
            array[(front + 8) % 8] = item;
            front = (front + 8) % 8 - 1;
            size++;
        } else {
            int index = (front) % 8; //the next item's index
            array[front] = item;
            front--;
            size++;
        }
    }

    public void addLast(T item) {
        int index = (rear + 1) % 8;//the next last item's index
        rear = index;
        if (array[index] == null) {
            array[index] = item;
        }
        if (rear == front && size == 8) {
            System.out.print("队列满了");
        }
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            int index = (front + i + 1) % size;
            System.out.print(array[index]);
            System.out.print(" ");
            i++;
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
        return array[index];
    }

/*test
    public static void main(String[] args) {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addFirst("first added");
        a1.addLast("second added");
        a1.addFirst("third added");
        a1.addFirst("kkkkkk");
        a1.addLast("hhh");
        a1.removeFirst();
        a1.removeLast();
    }*/
}