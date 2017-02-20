package edu.isep.algoprog.util.sort;

public class MergeSorter {
    int[] data;

    public MergeSorter(int[] data) {
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
        int[] tmp = new int[middle - begin];
        System.arraycopy(data, begin, tmp, 0, tmp.length);
        int i = 0, j = middle, dest = begin;
        while ((i < tmp.length) && (j < end)) {
            data[dest++] = (tmp[i] < data[j]) ? tmp[i++] : data[j++];
        }
        while (i < tmp.length) {
            data[dest++] = tmp[i++];
        }
    }
}
