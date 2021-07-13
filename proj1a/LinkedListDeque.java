public class LinkedListDeque<T> {
    private int size = 0;
    private TNode sentinal;
    private TNode tail;

    private class TNode {
        TNode pre;
        TNode next;
        T item;
        TNode(TNode p, T x, TNode n) {
            pre = p;
            next = n;
            item = x;
        }
    }

//    public LinkedListDeque(T x){
//        TNode t = new TNode(null, x,null);
//        sentinal = new TNode(null, null, t);
//        tail = new TNode(t, null,null);
//        if (t.pre == null) System.out.println("我想不通");
//        t.pre = sentinal;//为什么对了？？？t.pre难道不是null吗？？？
//        t.next = tail;
//        size = 1;
//    }
    public LinkedListDeque() {
        sentinal = new TNode(null, null, null);
        tail = new TNode(sentinal, null, null);
        sentinal = new TNode(null, null, tail);
        tail.pre = sentinal;
    }

    public void addFirst(T item) {
        TNode f = new TNode(sentinal, item, sentinal.next);
        f.next.pre = f;
        sentinal.next = f;
        size += 1;
    }

    public void addLast(T item) {
        TNode l = new TNode(tail.pre, item, tail);
        tail.pre = l;
        l.pre.next = l;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinal;
        while (p != tail) {
            System.out.println(p.item);
            p = p.next;
        }
    }


    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T first = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.next.pre = sentinal;
        size--;
        return first;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T last = tail.pre.item;
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        size--;
        return last;
    }

    public T get(int index) {
        int i = 0;
        TNode p = sentinal;
        while (i <= index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return helper(sentinal.next, index);
    }
    private T helper(TNode s, int index) {
        if (index == 0) {
            return s.item;
        } else {
            return helper(s.next, index - 1);
        }
    }

}
