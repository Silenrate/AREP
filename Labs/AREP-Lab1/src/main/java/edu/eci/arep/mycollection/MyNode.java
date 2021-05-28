package edu.eci.arep.mycollection;

import java.util.Objects;

/**
 * My own implementation of a Node.
 *
 * @param <E> The Value Type of the My Node.
 */
public class MyNode<E> {

    private MyNode<E> next;
    private E value;

    /**
     * Constructor of MyNode
     *
     * @param value data value of the node
     */
    public MyNode(E value) {
        this.next = null;
        this.value = value;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    /**
     * Determine if the Node has another Node linked it.
     *
     * @return The boolean Value that determines if the Node has another Node linked it.
     */
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyNode<?> myNode = (MyNode<?>) o;
        return Objects.equals(next, myNode.next) && Objects.equals(value, myNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, value);
    }
}

