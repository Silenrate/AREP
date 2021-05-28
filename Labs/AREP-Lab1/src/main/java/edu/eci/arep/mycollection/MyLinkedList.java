package edu.eci.arep.mycollection;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * My Own Implementation Of A Linked List, I know that is very inefficient.
 *
 * @param <E> The Element Type of the Collection.
 */
public class MyLinkedList<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, Serializable {

    private int length;
    private MyNode<E> first;
    private MyNode<E> last;

    public static final String NOT_SUPPORTED_EXCEPTION = "Not supported yet.";
    public static final String NO_SUCH_ELEMENT_EXCEPTION = "The Collection is Empty";

    /**
     * Empty Constructor For The Linked List.
     */
    public MyLinkedList() {
        length = 0;
        first = null;
        last = null;
    }

    /**
     * Generate The List Iterator of My Linked List.
     *
     * @param index The index to travel around My Linked List.
     * @return The List Iterator of My Linked List.
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIterator<E>() {
            final MyNode<E> firstNode = first;

            @Override
            public boolean hasNext() {
                return firstNode.hasNext();
            }

            @Override
            public E next() {
                if (!firstNode.hasNext()) {
                    throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);
                }
                int counter = 0;
                MyNode<E> currentNode = firstNode;
                while (currentNode.hasNext()) {
                    if (counter == index) {
                        break;
                    }
                    counter++;
                    currentNode = currentNode.getNext();
                }
                return currentNode.getValue();
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
            }

            @Override
            public void add(E e) {
                addLast(e);
            }
        };
    }

    @Override
    public void addFirst(E e) {
        MyNode<E> node = new MyNode<>(e);
        if (length > 0) {
            MyNode<E> oldFirst = first;
            node.setNext(oldFirst);
        }
        first = node;
        length++;
    }

    @Override
    public void addLast(E e) {
        MyNode<E> node = new MyNode<>(e);
        if (length == 0) {
            first = node;
        } else {
            MyNode<E> currentNode = first;
            while (currentNode.hasNext()) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(node);
            last = node;
        }
        length++;
    }

    @Override
    public boolean offerFirst(E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public boolean offerLast(E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);
        }
        length--;
        MyNode<E> oldFirst = first;
        first = oldFirst.getNext();
        return oldFirst.getValue();
    }

    @Override
    public E removeLast() {
        if (length == 0) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);
        }
        MyNode<E> currentNode = first;
        MyNode<E> newLast = null;
        while (currentNode.hasNext()) {
            if (currentNode.getNext().equals(last)) {
                newLast = currentNode;
            }
            currentNode = currentNode.getNext();
        }
        last = newLast;
        length--;
        return currentNode.getValue();
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E getFirst() {
        return first.getValue();
    }

    @Override
    public E getLast() {
        return last.getValue();
    }

    @Override
    public E peekFirst() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E peekLast() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public void push(E e) {
        addLast(e);
    }

    @Override
    public E pop() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION);
    }

    @Override
    public String toString() {
        String stringValue = "[";
        if (length > 0) {
            stringValue = stringValue + first.getValue();
            MyNode<E> currentNode = first;
            while (currentNode.hasNext()) {
                stringValue = stringValue + ", ";
                currentNode = currentNode.getNext();
                stringValue = stringValue + currentNode.getValue();
            }
        }
        stringValue = stringValue + "]";
        return stringValue;
    }
}
