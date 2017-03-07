package edu.isep.algoprog.sort;

public enum SorterImplementation {
    BubbleSorterImplementation("Bubble sort"),
    MergeSorterImplementation("Merge sort"),
    QuickSorterImplementation("Quick sort"),
    SelectionSorterImplementation("Selection sort");

    private String algorithmName;
    SorterImplementation(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
