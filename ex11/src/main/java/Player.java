import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player implements Runnable {

    private String name;
    private Dange dange;

    @Override
    public void run() {
        Thread.currentThread().setName(this.name);
        System.out.println(MessageStorage.playerIsReady());
        this.dange.startPreparePhase();
    }
}
