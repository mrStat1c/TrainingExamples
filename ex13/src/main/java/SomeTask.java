import java.util.Random;
import java.util.concurrent.Callable;

public class SomeTask implements Callable<Boolean> {
    @Override
    public Boolean call() throws InterruptedException {
        return new Random().nextBoolean();
    }
}
