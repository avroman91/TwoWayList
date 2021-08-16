package ua.ithillel.twoWayList;

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

    public void addFirst(T element) {
        if (tail != null) {
            add(0, element);
        } else {
            tail = head = new ListItem<>(element);
        }
    }

    public void addLast(T element) {
        add(element);
    }

    public void add(int index, T element) {
        ListItem<T> elemAtIndex = new ListItem<>(element);
        ListItem<T> current = head;
        if (index == 0 && index < size && tail != null) {
            ListItem<T> next = head;
            head = elemAtIndex;
            head.next = next;
            head.next.previous = head;

        } else if (index == size - 1 && tail != null) {
            add(element);
        }
        if (index != 0) {
            while (index != 0) {
                current = current.next;
                index--;
            }
            current.previous.next = elemAtIndex;
            elemAtIndex.previous = current.previous;
            current.previous = elemAtIndex;
            elemAtIndex.next = current;
        }
        size++;
    }

    public void addAll(TwoWayList<T> list) {
        ListItem<T> current = list.head;
        for (int i = 0; i < list.size; i++) {
            add(current.value);
            current = current.next;
        }
    }

    public void addAll(int index, TwoWayList<T> list) {
        ListItem<T> current = list.head;
        int i = index;
        while (i != 0) {
            add(index, current.value);
            current = current.next;
            i--;
            index++;
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
