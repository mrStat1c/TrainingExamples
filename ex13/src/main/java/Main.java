import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Callable<Boolean>> tasks = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> tasks.add(new SomeTask()));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Boolean> resultList = executorService.invokeAll(tasks)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println("Task failed!");
                        return false;
                    }
                }).collect(Collectors.toList());
        resultList.forEach(result -> System.out.println("Result = " + result));
        int trueCount = (int) resultList.stream().filter(result -> result.equals(true)).count();
        int falseCount = (int) resultList.stream().filter(result -> result.equals(false)).count();
        System.out.println("Final result = " + (trueCount >= falseCount));
        executorService.shutdown();
    }
}
