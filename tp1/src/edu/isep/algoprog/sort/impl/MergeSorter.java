package edu.isep.algoprog.sort.impl;

import edu.isep.algoprog.sort.AbstractSorter;

public class MergeSorter extends AbstractSorter {
    private int[] data;

    public MergeSorter(int[] data) {
        super(data);
        this.data = data;
    }

    public int[] getData() {
        return this.data;
    }

    public void sort() {
        sort(0, data.length);
    }

    private void sort(int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int middle = (end + begin) / 2;
        sort(begin, middle);
        sort(middle, end);
        mergeSorted(begin, middle, end);
    }

    private void mergeSorted(int begin, int middle, int end) {
        // Copy the first half into a temp array :
        int[] tmp = new int[middle - begin];
        System.arraycopy(data, begin, tmp, 0, tmp.length);

        // Iterate over the array to put in it the smallest value between :
        // - content coming from the tmp array (in that case, increment the tmp cursor, i)
        // - its own content coming from the second half (in that case, increment the cursor for the second half, j)
        int i = 0, j = middle, dest = begin;
        while ((i < tmp.length) && (j < end)) {
            data[dest++] = (tmp[i] < data[j]) ? tmp[i++] : data[j++];
        }

        // Put back in the array the remaining of the tmp array (we're replacing values we have already used during the
        // first loop)
        while (i < tmp.length) {
            data[dest++] = tmp[i++];
        }
    }
}
