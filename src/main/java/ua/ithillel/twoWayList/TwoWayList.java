package ua.ithillel.twoWayList;

import java.util.Iterator;

public class TwoWayList<T> implements Iterable<T> {

    private ListItem<T> head;
    private ListItem<T> tail;
    private int size = 0;

    public void add(T element) {
        ListItem<T> lastItem = new ListItem<>(element);
        size++;
        if (tail != null) {
            ListItem<T> previous = tail;
            tail.next = lastItem;
            tail = lastItem;
            tail.previous = previous;
        } else {
            tail = head = lastItem;
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

        if (index >= size || index < 0) return;

        if (index == 0 && tail != null) {
            ListItem<T> next = head;
            head = elemAtIndex;
            head.next = next;
            head.next.previous = head;
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
        ListItem<T> current = list.tail;
        for (int i = index; i > 0; i--) {
            add(index, current.value);
            current = current.previous;
        }
    }

    public void clean() {
        head = tail = null;
        size = 0;
    }

    public void removeByIndex(int index) {
        if (index == size - 1) {
            tail.previous.next = null;
            tail = tail.previous;
            size--;
            return;
        }
        if (index == 0) {
            head.next.previous = null;
            head = head.next;
            size--;
            return;
        }
        ListItem<T> current = head;
        for (int i = index; i > 0; i--) {
            current = current.next;
        }
        current.next.previous = current.previous;
        current.previous.next = current.next;
        size--;
    }

    public void remove(T elem) {
        ListItem<T> current = head;
        int index = 0;
        while (index < size && current.value != elem) {
            index++;
            current = current.next;
        }
        if (index < size) {
            removeByIndex(index);
        }
    }

    public boolean contains(T elem) {
        ListItem<T> current = head;
        int index = 0;
        while (index < size && current.value != elem) {
            index++;
            current = current.next;
        }
        return index < size;
    }

    public void set(int index, T elem) {
        ListItem<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = elem;
    }

    public ListItem<T> getFirst() {
        return head;
    }

    public ListItem<T> getLast() {
        return tail;
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
