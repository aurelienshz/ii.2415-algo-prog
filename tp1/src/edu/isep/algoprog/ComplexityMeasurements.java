package edu.isep.algoprog;

import edu.isep.algoprog.util.ArrayUtils;
import edu.isep.algoprog.util.RandomData;
import edu.isep.algoprog.util.TimeUtils;
import edu.isep.algoprog.util.XYLineChart_AWT;

import java.util.HashMap;
import java.util.function.Consumer;

public class ComplexityMeasurements {
    // Hashmaps <input length, execution time> for every algorithm
    // Will be used to draw graphs :
    private HashMap<Long, Long> findMinDurations;
    private HashMap<Long, Long> mergeSortDurations;
    private HashMap<Long, Long> selectionSortDurations;
    private HashMap<Long, Long> bubbleSortDurations;
    private HashMap<Long, Long> quickSortDurations;
    private HashMap<Long, Long> dichotomyDurations;

    // Datasets that will be generated later, used as dummy data to test the time complexity of the algorithms
    private int[][] datasets;

    // time utils : useful to get CPU time of the thread to be able to measure the time complexity of
    // an algorithm
    private TimeUtils timeUtils;

    private static final int DATASET_LENGTH_MIN = 0;
    private static final int DATASET_LENGTH_MAX = 10000;
    private static final int DATASET_LENGTH_STEP = 300;
    private static final int DATASET_MIN_VALUE = 0;
    private static final int DATASET_MAX_VALUE = DATASET_LENGTH_MAX * DATASET_LENGTH_STEP;

    private int[][] generateRandomDatasets() {
        int index = 0;
        int numberOfDatasets = ((DATASET_LENGTH_MAX - DATASET_LENGTH_MIN) / DATASET_LENGTH_STEP) + 1;
        int[][] datasets = new int[numberOfDatasets][];
        for (
                int datasetLength = DATASET_LENGTH_MIN;
                datasetLength <= DATASET_LENGTH_MAX;
                datasetLength += DATASET_LENGTH_STEP) {
            int[] data = RandomData.generate1d(datasetLength, DATASET_MIN_VALUE, DATASET_MAX_VALUE);
            datasets[index] = data;
            index++;
        }
        return datasets;
    }

    ComplexityMeasurements() {
        this.timeUtils = new TimeUtils();
        this.datasets = generateRandomDatasets();
    }

    void runMeasurements() {
        this.findMinDurations = computeFindMinDurations();
        this.mergeSortDurations = computeMergeSortDurations();
        this.selectionSortDurations = computeSelectionSortDurations();
        this.bubbleSortDurations = computeBubbleSortDurations();
        this.quickSortDurations = computeQuickSortDurations();
        this.dichotomyDurations = computeDichotomyDurations();
    }

    void drawGraphs() {
        XYLineChart_AWT chart = new XYLineChart_AWT("findMin time complexity");
        chart.drawHashmap(findMinDurations, "findMinDurations");

        chart = new XYLineChart_AWT("Merge sort time complexity");
        chart.drawHashmap(mergeSortDurations, "mergeSortDurations");

        chart = new XYLineChart_AWT("Selection sort time complexity");
        chart.drawHashmap(selectionSortDurations, "selectionSortDurations");

        chart = new XYLineChart_AWT("Bubble sort time complexity");
        chart.drawHashmap(bubbleSortDurations, "bubbleSortDurations");

        chart = new XYLineChart_AWT("Quick sort time complexity");
        chart.drawHashmap(quickSortDurations, "quickSortDurations");

//        XYLineChart_AWT.graphHashMap(findMin, "findMin");
    }

    void drawSortGraphs() {
        XYLineChart_AWT chart = new XYLineChart_AWT("All sorting algorithms");
        chart.setChartTitle("All sorting algorithms");

        chart.addHashmapToDataset(mergeSortDurations, "Merge sort");
        chart.addHashmapToDataset(selectionSortDurations, "Selection sort");
        chart.addHashmapToDataset(bubbleSortDurations, "Bubble sort");
        chart.addHashmapToDataset(quickSortDurations, "Quick sort");
        chart.drawGraph();
    }

    private HashMap<Long, Long> computeFunctionDuration(Consumer<ArrayUtils> consumer) {
        HashMap<Long, Long> durations = new HashMap<>(datasets.length);

        for (int[] data : datasets) {
            ArrayUtils arrayUtils = new ArrayUtils(data);
            timeUtils.startTimer();

            consumer.accept(arrayUtils);

            timeUtils.endTimer();
            durations.put((long) data.length, timeUtils.getDuration());
        }

        return durations;
    }

    private HashMap<Long, Long> computeFindMinDurations() {
        return computeFunctionDuration(ArrayUtils::findMin);
    }

    private HashMap<Long, Long> computeMergeSortDurations() {
        return computeFunctionDuration(ArrayUtils::mergeSort);
    }

    private HashMap<Long, Long> computeSelectionSortDurations() {
        return computeFunctionDuration(ArrayUtils::selectionSort);
    }

    private HashMap<Long, Long> computeQuickSortDurations() {
        return computeFunctionDuration(ArrayUtils::quickSort);
    }

    private HashMap<Long, Long> computeBubbleSortDurations() {
        return computeFunctionDuration(ArrayUtils::bubbleSort);
    }

    private HashMap<Long, Long> computeDichotomyDurations() {
        System.out.println("TODO : Dichotomy durations"); // TODO
        HashMap<Long, Long> durations = new HashMap<>(datasets.length);
        return durations;
    }
}
