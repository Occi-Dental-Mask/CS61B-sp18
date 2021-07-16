public class LinkedListDeque<T> implements Deque<T> {
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

    public LinkedListDeque() {
        sentinal = new TNode(null, null, null);
        tail = new TNode(sentinal, null, null);
        sentinal = new TNode(null, null, tail);
        tail.pre = sentinal;
    }

    /* Add one item in the front */
    @Override
    public void addFirst(T item) {
        TNode f = new TNode(sentinal, item, sentinal.next);
        f.next.pre = f;
        sentinal.next = f;
        size += 1;
    }

    /* Add one item in the last */
    @Override
    public void addLast(T item) {
        TNode l = new TNode(tail.pre, item, tail);
        tail.pre = l;
        l.pre.next = l;
        size += 1;
    }

    /* whether the list is empty(only have a sentinal */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TNode p = sentinal;
        while (p != tail) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    @Override
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

    @Override
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

    @Override
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
