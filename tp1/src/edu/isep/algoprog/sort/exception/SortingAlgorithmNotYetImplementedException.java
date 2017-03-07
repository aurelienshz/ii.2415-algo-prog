package edu.isep.algoprog.sort.exception;

public class SortingAlgorithmNotYetImplementedException extends Exception {
    @Override
    public String getMessage() {
        return "The requested sorting algorithm was not yet implemented";
    }
}
