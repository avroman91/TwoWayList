package ua.ithillel.twoWayList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        TwoWayList<Integer> list = new TwoWayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.addFirst(0);
        list.add(2,9);
        list.add(2,9);
        TwoWayList<Integer> list2 = new TwoWayList();
        list2.add(33);
        list2.add(44);
        list.addAll(2,list2);


        Iterator<Integer> iter = list.iterator();
        System.out.println("----------------------");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("----------------------");
        Iterator<Integer> reverseIter = list.reverseIterator();
        while (reverseIter.hasNext()) {
            System.out.println(reverseIter.next());
        }
    }
}
