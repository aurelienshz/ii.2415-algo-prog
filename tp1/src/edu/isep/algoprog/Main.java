package edu.isep.algoprog;

public class Main {
    public static void main(String[] args) {
        // warmup : call all methods once so that all the required classes are loaded by the JVM
        // prevents losing time when trying to accurately measure the execution time
        ComplexityMeasurements complexityMeasurements = new ComplexityMeasurements();

        complexityMeasurements.runMeasurements(); // warmup

        complexityMeasurements.runMeasurements();

        complexityMeasurements.drawGraphs();

        complexityMeasurements.drawSortGraphs();
    }
}
