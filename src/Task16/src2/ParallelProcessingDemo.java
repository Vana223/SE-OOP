package src.Task16.src2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

public class ParallelProcessingDemo {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add(i);
        }

        findMinimum(data);

        findMaximum(data);
        
        calculateAverage(data);
    }

    private static void findMinimum(List<Integer> data) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            return data.stream().min(Integer::compareTo).orElseThrow(NoSuchElementException::new);
        });
        try {
            int min = future.get();
            System.out.println("Minimum value: " + min);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void findMaximum(List<Integer> data) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            return data.stream().max(Integer::compareTo).orElseThrow(NoSuchElementException::new);
        });
        try {
            int max = future.get();
            System.out.println("Maximum value: " + max);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void calculateAverage(List<Integer> data) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(() -> {
            double sum = data.stream().mapToInt(Integer::intValue).sum();
            return sum / data.size();
        });
        try {
            double average = future.get();
            System.out.println("Average value: " + average);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
