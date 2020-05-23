public class DangeSpeaker implements Runnable {

    private String message;

    public DangeSpeaker(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
    }
}
