package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class PerformanceTracker {

    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long recursiveCalls;
    private long startTime;
    private long endTime;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        recursiveCalls = 0;
        startTime = 0;
        endTime = 0;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public void incrementRecursiveCalls() {
        recursiveCalls++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    public long getExecutionTimeNano() {
        return endTime - startTime;
    }

    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void exportToCSV(String filePath, String algorithmName, int inputSize) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(String.format(
                    "%s,%d,%d,%d,%d,%d,%.3f,%s%n",
                    algorithmName,
                    inputSize,
                    comparisons,
                    swaps,
                    arrayAccesses,
                    recursiveCalls,
                    getExecutionTimeMillis(),
                    LocalDateTime.now()
            ));
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
