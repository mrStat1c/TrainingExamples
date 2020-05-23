import lombok.SneakyThrows;

public class Chest {

    public int requiredLootTime;
    private Dange dange;

    public Chest(Dange dange) {
        this.requiredLootTime = Math.max((int)(Math.random() * 10), 1);
        this.dange = dange;
    }

    @SneakyThrows
    public void loot(){
        String playerName = Thread.currentThread().getName();
        this.dange.getDangeResults().addLootedChest(playerName);
        Thread.sleep(this.requiredLootTime * 1000);
        System.out.println(MessageStorage.playerLootedChest(playerName, this.requiredLootTime));
    }
}
