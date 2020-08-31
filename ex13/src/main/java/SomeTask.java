import java.util.Random;
import java.util.concurrent.Callable;

public class SomeTask implements Callable<Boolean> {
    @Override
    public Boolean call() throws InterruptedException {
//        int sleepTime = (int)(Math.random() * 6 + 1) * 1000;
//        System.out.println("SleepTime = " + sleepTime);
//        Thread.sleep(sleepTime);
        return new Random().nextBoolean();
    }
}
