package ua.ithillel.linkedList;

import java.util.Iterator;

public class TwoWayList<T> implements Iterable<T> {

    private ListItem<T> head;
    private ListItem<T> tail;
    private int size = 0;

    public void add(T element) {
        ListItem<T> listItem = new ListItem<>(element);
        size++;
        if (tail != null) {
            ListItem<T> previous = tail;
            tail.next = listItem;
            tail = listItem;
            tail.previous = previous;
        } else {
            tail = head = listItem;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ListItem<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    public Iterator<T> reverseIterator() {
        return new Iterator<T>() {
            ListItem<T> current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.previous;
                return value;
            }
        };
    }

    public static class ListItem<T> {
        T value;
        ListItem<T> next;
        ListItem<T> previous;

        public ListItem(T value) {
            this.value = value;
        }
    }
}
