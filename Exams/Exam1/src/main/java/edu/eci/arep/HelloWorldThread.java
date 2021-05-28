package edu.eci.arep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class HelloWorldThread extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Hello World at " + LocalDateTime.now());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloWorldThread thread = new HelloWorldThread();
        List<Callable<String>> callableTasks = new ArrayList<>();
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("1");
            return "Task's execution";
        };
        Callable<String> callableTask2 = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("2");
            return "Task's execution 2";
        };
        callableTasks.add(callableTask);
        callableTasks.add(callableTask2);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            executor.invokeAny(callableTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Threads Finished at " + LocalDateTime.now());
        executor.shutdown();
    }
}
