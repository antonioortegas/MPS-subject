package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @param <T> - Generic type
 * @author Teodoro Hidalgo Guerrero
 */
public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        size = 0;
        last = null;
        first = null;
    }

    @Override
    public void prepend(T value) {
        DequeNode<T> newNode = new DequeNode<>(value, null, null);
        if (first == null) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }

        newNode.setNext(first);
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        DequeNode<T> newNode = new DequeNode<>(value, null, null);
        if (first == null) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }

        newNode.setPrevious(last);
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (first == null)
            throw new DoubleEndedQueueException("Queue is empty");

        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }

        size--;
    }

    @Override
    public void deleteLast() {
        if (last == null)
            throw new DoubleEndedQueueException("Queue is empty");

        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }

        size--;
    }

    @Override
    public T first() {
        if (first == null)
            throw new DoubleEndedQueueException("Queue is empty");

        return first.getItem();
    }

    @Override
    public T last() {
        if (last == null)
            throw new DoubleEndedQueueException("Queue is empty");

        return last.getItem();
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public T get(int index) {
        int i = 0;

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        DequeNode<T> current = first;

        while (i < index) {
            current = current.getNext();
            i++;
        }

        return current.getItem();
    }

    @Override
    public boolean contains(T value) {
        DequeNode<T> current = first;

        while (current != null) {
            if (Objects.equals(value, current.getItem()))
                break;
            current = current.getNext();
        }

        return current != null;
    }

    @Override
    public void remove(T value) {
        DequeNode<T> current = first;
        DequeNode<T> previous = null;

        while (current != null) {
            if (Objects.equals(value, current.getItem()))
                break;
            previous = current;
            current = current.getNext();
        }

        if (current == null)
            return;

        size--;

        if (current.isFirstNode()) {
            first = current.getNext();
            if (first != null)
                first.setPrevious(null);
        } else {
            previous.setNext(current.getNext());
        }

        if (current.isLastNode()) {
            last = current.getPrevious();
            if (last != null)
                last.setNext(null);
        } else {
            current.getNext().setPrevious(previous);
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if(size() == 0)
            return;

        List<DequeNode<T>> list = new ArrayList<>();

        DequeNode<T> current = first;
        while (current != null) {
            list.add(current);
            current = current.getNext();
        }

        list.sort(Comparator.comparing(DequeNode::getItem, comparator));

        first = list.get(0);
        first.setPrevious(null);
        first.setNext(null);

        for (int i = 1; i < list.size(); i++) {
            list.get(i).setPrevious(list.get(i - 1));
            list.get(i - 1).setNext(list.get(i));
        }

        last = list.get(list.size() - 1);
        last.setNext(null);

        list.clear();
    }
}
