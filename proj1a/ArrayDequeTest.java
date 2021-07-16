public class ArrayDequeTest {
    public static void main(String[] args) {
        int i = 0;
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for(i=0;i<10;i++){
            a.addFirst(i*i);
        }
        int m = a.get(8);//
        a.printDeque();
        ArrayDeque<Integer> b = new ArrayDeque<Integer>();
        for(i=0;i<10;i++){
            b.addLast(i*i);
        }
        b.printDeque();

        for(i=0;i<10;i++){
            b.removeLast();
            b.removeFirst();
        }
    }
}
