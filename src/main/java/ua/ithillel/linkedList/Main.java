package ua.ithillel.linkedList;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        TwoWayList<Integer> list = new TwoWayList();
        list.add(7);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(23);
//        Iterator<Integer> it = list.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

        Iterator<Integer> it2 = list.reverseIterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
}
