package edu.isep.algoprog;

import edu.isep.algoprog.util.ArrayUtils;
import edu.isep.algoprog.util.RandomData;
import edu.isep.algoprog.util.TimerUtils;
import edu.isep.algoprog.util.XYLineChart_AWT;
import edu.isep.algoprog.util.search.Dichotomy;

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
    private TimerUtils timerUtils;

    private static final int datasetMaxValue = Config.DATASET_LENGTH_MAX * Config.DATASET_LENGTH_STEP;

    ComplexityMeasurements() {
        this.timerUtils = new TimerUtils();
        this.datasets = generateRandomDatasets();
    }

    private int[][] generateRandomDatasets() {
        int index = 0;
        int numberOfDatasets = ((Config.DATASET_LENGTH_MAX - Config.DATASET_LENGTH_MIN) / Config.DATASET_LENGTH_STEP) + 1;
        int[][] datasets = new int[numberOfDatasets][];
        for (
                int datasetLength = Config.DATASET_LENGTH_MIN;
                datasetLength <= Config.DATASET_LENGTH_MAX;
                datasetLength += Config.DATASET_LENGTH_STEP) {
            int[] data = RandomData.generate1d(datasetLength, Config.DATASET_MIN_VALUE, datasetMaxValue);
            datasets[index] = data;
            index++;
        }
        return datasets;
    }

    void runMeasurements() {
        this.quickSortDurations = computeQuickSortDurations();
        this.findMinDurations = computeFindMinDurations();
        this.mergeSortDurations = computeMergeSortDurations();
        this.bubbleSortDurations = computeBubbleSortDurations();
        this.selectionSortDurations = computeSelectionSortDurations();
        this.dichotomyDurations = computeDichotomyDurations();
    }

    void drawGraphs() {
        new XYLineChart_AWT("findMin time complexity")
                .drawHashmap(findMinDurations, "findMinDurations");
        new XYLineChart_AWT("Merge sort time complexity")
                .drawHashmap(mergeSortDurations, "mergeSortDurations");
        new XYLineChart_AWT("Selection sort time complexity")
                .drawHashmap(selectionSortDurations, "selectionSortDurations");
        new XYLineChart_AWT("Bubble sort time complexity")
                .drawHashmap(bubbleSortDurations, "bubbleSortDurations");
        new XYLineChart_AWT("Quick sort time complexity")
                .drawHashmap(quickSortDurations, "quickSortDurations");
        new XYLineChart_AWT("Binary search time complexity")
                .drawHashmap(dichotomyDurations, "binary search durations");
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

    /**
     * Time an ArrayUtils method passed as a method reference
     *
     * How to call it : computeFunctionDuration(ArrayUtils::findMin); (method reference)
     * or computeFunctionDuration(au -> au.findMin()); (lambda)
     */
    private HashMap<Long, Long> computeFunctionDuration(Consumer<ArrayUtils> consumer) {
        HashMap<Long, Long> durations = new HashMap<>(datasets.length);

        for (int[] data : datasets) {
            ArrayUtils arrayUtils = new ArrayUtils(data.clone());
            timerUtils.startTimer();

            consumer.accept(arrayUtils);

            timerUtils.endTimer();
            durations.put((long) data.length, timerUtils.getDuration());
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
        int numberOfDatasets = ((Config.DICHOTOMY_LENGTH_MAX - Config.DICHOTOMY_LENGTH_MIN) / Config.DICHOTOMY_LENGTH_STEP) + 1;
        HashMap<Long, Long> durations = new HashMap<>(numberOfDatasets);

        for (
                int datasetLength = Config.DICHOTOMY_LENGTH_MIN;
                datasetLength <= Config.DICHOTOMY_LENGTH_MAX;
                datasetLength += Config.DICHOTOMY_LENGTH_STEP) {

            Dichotomy dichotomy = new Dichotomy(datasetLength);
            int toFind = 0;

            timerUtils.startTimer();
            int index = dichotomy.findIndex(toFind);
            timerUtils.endTimer();

            if (index == -1) System.out.println("T'as merdé, Jack");
            System.out.println("Longueur : " + datasetLength);
            System.out.println("toFind : " + toFind);
            System.out.println("index : " + index);
            System.out.println("check : " + index);
            System.out.println("");


            durations.put((long) datasetLength, timerUtils.getDuration());
        }
        return durations;
    }
}
