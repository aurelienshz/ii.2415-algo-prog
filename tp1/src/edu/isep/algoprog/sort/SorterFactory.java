package edu.isep.algoprog.sort;

import edu.isep.algoprog.sort.exception.SortingAlgorithmNotYetImplementedException;
import edu.isep.algoprog.sort.impl.BubbleSorter;
import edu.isep.algoprog.sort.impl.MergeSorter;
import edu.isep.algoprog.sort.impl.QuickSorter;
import edu.isep.algoprog.sort.impl.SelectionSorter;

public class SorterFactory {
    public static AbstractSorter createSorter(SorterImplementation sortingImplementation, int[] data) throws SortingAlgorithmNotYetImplementedException {
        switch (sortingImplementation) {
            case BubbleSorterImplementation:
                return new BubbleSorter(data);
            case MergeSorterImplementation:
                return new MergeSorter(data);
            case QuickSorterImplementation:
                return new QuickSorter(data);
            case SelectionSorterImplementation:
                return new SelectionSorter(data);
        }
        throw new SortingAlgorithmNotYetImplementedException();
    }
}
