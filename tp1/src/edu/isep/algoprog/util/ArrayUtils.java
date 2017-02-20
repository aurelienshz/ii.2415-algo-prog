package edu.isep.algoprog.util;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import edu.isep.algoprog.util.sort.BubbleSorter;
import edu.isep.algoprog.util.sort.MergeSorter;
import edu.isep.algoprog.util.sort.QuickSorter;
import edu.isep.algoprog.util.sort.SelectionSorter;

public class ArrayUtils {
    private int[] data;

    public ArrayUtils(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }

    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public int findMin() {
        // We assume that values are > 0
        int min = -1;
        for (int i : data) {
            if (i > min) min = i;
        }
        return min;
    }

    public void bubbleSort() {
        BubbleSorter bubbleSorter = new BubbleSorter(data);
        bubbleSorter.sort();
    }

    public void mergeSort() {
        MergeSorter mergeSorter = new MergeSorter(data);
        mergeSorter.sort();
    }

    public void selectionSort() {
        SelectionSorter selectionSorter = new SelectionSorter(data);
        selectionSorter.sort();
    }

    public void quickSort() {
        QuickSorter quickSorter = new QuickSorter(data);
        quickSorter.sort();
    }

    public void printData() {
        System.out.println("");
        System.out.println("===========================");
        System.out.println("=== Array length : " + data.length + " ===");
        for (int i : data) {
            System.out.println(i);
        }
        System.out.println("=== Array length : " + data.length + " ===");
        System.out.println("===========================");
        System.out.println("");
    }
}
