package edu.isep.algoprog.util.sort;

import edu.isep.algoprog.util.ArrayUtils;

public class QuickSorter extends ArrayUtils {
    private int[] data;

    public QuickSorter(int[] data) {
        super(data);
        this.data = data;
    }

    public int partition(int begin, int end, int pivotIdx) {
        swap(pivotIdx, --end);

        pivotIdx = end;
        int pivot = data[pivotIdx];

        // invariant is that everything before begin is known to be < pivot
        // and everything after end is known to be >= pivot
        while (begin != end) {
            if (data[begin] >= pivot) {
                swap(begin, --end);
            } else {
                ++begin;
            }
        }
        swap(pivotIdx, begin);
        return begin;
    }

    public void sort() {
        sort(0, data.length);
    }

    public void sort(int begin, int end){
        if((end-begin) < 2){ return; }
        int m = partition(begin, end, (end+begin)/2);
        sort(begin, m);
        sort(m+1, end); // +1 for convergence
    }
}
