import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Dange {

    private int requiredPlayerCount = 4;
    private CyclicBarrier dangeBarrier = new CyclicBarrier(this.requiredPlayerCount);
    private volatile Set<String> speckedMessages = new HashSet<>();
    private Boss boss = new Boss(1000, this);
    private int chestCount = 20;
    private BlockingQueue<Chest> chestes = new ArrayBlockingQueue<>(this.chestCount);
    @Getter
    public DangeResults dangeResults = new DangeResults();


    @SneakyThrows
    public Dange() {
        for(int i = 0; i < this.chestCount; i++){
            this.chestes.put(new Chest(this));
        }
    }

    @SneakyThrows
    public void startPreparePhase() {
        this.dangeBarrier.await();
        System.out.println(MessageStorage.playerWentToDange());
        startBossFightPhase();
    }

    @SneakyThrows
    private void startBossFightPhase() {
        craftNewBarrier(this.requiredPlayerCount, "Boss Fight started! Boss hp = " + this.boss.getHp());
        this.dangeBarrier.await();
        while (this.boss.isAlive()) {
            Thread.sleep(PlayerParameters.PrepareTime.random() * 1000);
            this.boss.takeDamage(PlayerParameters.Damage.random());
            Thread.yield();
            Thread.sleep(10);
        }
        craftNewBarrier(this.requiredPlayerCount, "Boss Fight finished!");
        this.dangeBarrier.await();
        startLootPhase();
    }

    @SneakyThrows
    private void startLootPhase() {
        craftNewBarrier(this.requiredPlayerCount, "Chests loot started!");
        this.dangeBarrier.await();
        while (!this.chestes.isEmpty()) {
            Chest chest = this.chestes.poll();
            chest.loot();
        }
        craftNewBarrier(this.requiredPlayerCount, "LootPhase finished. Dangeon finished!");
        this.dangeBarrier.await();
    }

    private synchronized void craftNewBarrier(int playerCount, String message) {
        if (!this.speckedMessages.contains(message)) {
            this.dangeBarrier = new CyclicBarrier(playerCount, new DangeSpeaker(message));
            this.speckedMessages.add(message);
        }
    }
}
