package edu.eci.arep.sorts;

import java.util.List;

/**
 * Parametrized Class That Sorts The Elements Of A Collection With Bubble Sort Algorithm.
 *
 * @param <T> The type of the Collections Elements
 */
public class BubbleSort<T extends Comparable<? super T>> {


    /**
     * Returns The Ordered List With Bubble Sort Algorithm,
     *
     * @param list The Unordered List.
     * @return The Ordered List.
     */
    public List<T> bubbleSort(List<T> list) {
        int n = list.size();
        while (n > 0) {
            int lastModifiedIndex = 0;
            for (int currentIndex = 1; currentIndex < n; currentIndex++) {
                if (list.get(currentIndex - 1).compareTo(list.get(currentIndex)) > 0) {
                    T temp = list.get(currentIndex - 1);
                    list.set(currentIndex - 1, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    lastModifiedIndex = currentIndex;
                }
            }
            n = lastModifiedIndex;
        }
        return list;
    }
}