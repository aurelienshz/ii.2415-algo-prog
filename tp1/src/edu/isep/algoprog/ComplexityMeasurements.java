package edu.isep.algoprog;

import edu.isep.algoprog.findmin.MinimumFinder;
import edu.isep.algoprog.sort.AbstractSorter;
import edu.isep.algoprog.sort.SorterFactory;
import edu.isep.algoprog.sort.SorterImplementation;
import edu.isep.algoprog.sort.exception.SortingAlgorithmNotYetImplementedException;
import edu.isep.algoprog.util.RandomData;
import edu.isep.algoprog.util.Timer;
import edu.isep.algoprog.util.ChartApplicationFrame;
import edu.isep.algoprog.search.Dichotomy;

import java.util.HashMap;

public class ComplexityMeasurements {
    // Hashmaps <input length, execution time> for findMin and dichotomy
    private HashMap<Long, Long> findMinDurations;
    private HashMap<Long, Long> dichotomyDurations;

    // All the complexity measurements for the sorting algorithms, in a single hashmap. Yeah. That's how we do it around here.
    private HashMap<SorterImplementation, HashMap<Long, Long>> complexityMeasurementsResults = new HashMap<>(SorterImplementation.values().length);

    // Datasets that will be generated later, used as dummy data to test the time complexity of the algorithms
    private int[][] datasets;

    // time utils : useful to get CPU time of the thread to be able to measure the time complexity of
    // an algorithm
    private Timer timer = new Timer();

    private static final int datasetMaxValue = Config.DATASET_LENGTH_MAX * Config.DATASET_LENGTH_STEP;

    ComplexityMeasurements() {
        this.datasets = generateRandomDatasets();
    }

    /**
     * Run measurements for min finding and dichotomy, then for all implemented sorting algorithms
     */
    void runMeasurements() {
        this.findMinDurations = computeFindMinDurations();
        this.dichotomyDurations = computeDichotomyDurations();

        for (SorterImplementation impl: SorterImplementation.values()) {
            impl.getAlgorithmName();
            HashMap<Long, Long> durations = null;
            try {
                durations = computeSortFunctionDuration(impl);
            } catch (SortingAlgorithmNotYetImplementedException e) {
                System.out.println("Some algorithm present in the sorting algorithms implementation " +
                        "enum was not implemented in the SorterFactory.");
                System.out.println("Nested exception is : e.getMessage()");
                e.printStackTrace();
            }
            complexityMeasurementsResults.put(impl, durations);
        }
    }

    /**
     * Generate the configured number of random datasets to run our algorithms on them
     */
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

    /**
     * Draw all graphs for the measurements previously made :
     */
    void drawGraphs() {
        new ChartApplicationFrame("findMin time complexity")
                .drawHashmap(findMinDurations, "findMinDurations");
        new ChartApplicationFrame("Binary search time complexity")
                .drawHashmap(dichotomyDurations, "binary search durations");

        for (SorterImplementation impl: SorterImplementation.values()) {
            // Prepare String values and dataset to be graphed :
            String chartName = impl.getAlgorithmName() + " time complexity";
            String seriesLabel = impl.getAlgorithmName() + " durations";
            HashMap<Long, Long> complexityMeasurementsResult = complexityMeasurementsResults.get(impl);

            // Fire !
            new ChartApplicationFrame(chartName).drawHashmap(complexityMeasurementsResult, seriesLabel);
        }
    }

    /**
     * Draw all sorting algorithm time measurements on a single graph
     */
    void drawAllSortGraphs() {
        ChartApplicationFrame chart = new ChartApplicationFrame("All sorting algorithms");
        chart.setChartTitle("All sorting algorithms");

        for (SorterImplementation impl: SorterImplementation.values()) {
            chart.addHashmapToDataset(complexityMeasurementsResults.get(impl), impl.getAlgorithmName());
        }
        chart.drawGraph();
    }

    /**
     * Time an implementation of a sorting algorithm :
     */
    private HashMap<Long, Long> computeSortFunctionDuration(SorterImplementation impl) throws SortingAlgorithmNotYetImplementedException {
        HashMap<Long, Long> durations = new HashMap<>(datasets.length);
        for (int[] data : datasets) {
            // Create a new sorter with a new clone of the data :
            AbstractSorter sorter = SorterFactory.createSorter(impl, data.clone());

            timer.startTimer();
            sorter.sort();
            timer.endTimer();
            durations.put((long) data.length, timer.getDuration());
        }

        return durations;
    }

    private HashMap<Long, Long> computeFindMinDurations() {
        HashMap<Long, Long> durations = new HashMap<>(datasets.length);
        for (int[] data : datasets) {
            // Create a new sorter with a new clone of the data :
            MinimumFinder minimumFinder = new MinimumFinder(data);

            timer.startTimer();
            minimumFinder.findMin();
            timer.endTimer();
            durations.put((long) data.length, timer.getDuration());
        }
        return durations;
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

            timer.startTimer();
            int index = dichotomy.findIndex(toFind);
            timer.endTimer();

            if (index == -1) System.out.println("Element not found");

            durations.put((long) datasetLength, timer.getDuration());
        }
        return durations;
    }
}
