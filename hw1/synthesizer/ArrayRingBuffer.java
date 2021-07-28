// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import org.junit.Test;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            //least recently插入的Item序号 index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;//最近插入的item的下一个的序号
    /* Array for storing the buffer data. */
    private T[] rb;

    public Iterator<T>  iterator() {
        return new KeyIterator();
    }
    private class KeyIterator implements Iterator<T> {
        private int ptr;
        public KeyIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return ptr < last;
        }
        public T next() {
            T  returned = peek();
            ptr = (ptr + 1) % capacity;
            return returned;
        }
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (!isFull()) {
            fillCount++;
            rb[last] = x;
            last = (last + 1) % capacity;
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first
        if (!isEmpty()) {
            fillCount--;
            T returned = rb[first];
            first = (first + 1) % capacity;
            return returned;
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }
    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.




}
