import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        Dange dange = new Dange();

        Thread player1 = new Thread(new Player("Barny Stinson", dange));
        Thread player2 = new Thread(new Player("Diego", dange));
        Thread player3 = new Thread(new Player("Mr.Stat1c", dange));
        Thread player4 = new Thread(new Player("Half-True", dange));

        player1.start();
        player2.start();
        player3.start();
        player4.start();

        player1.join();
        player2.join();
        player3.join();
        player4.join();

        dange.getDangeResults().announce();
    }
}
