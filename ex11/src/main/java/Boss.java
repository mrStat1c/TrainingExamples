import java.util.concurrent.atomic.AtomicInteger;

public class Boss {

    private AtomicInteger hp;
    private Dange dange;

    public Boss(int hp, Dange dange) {
        this.hp = new AtomicInteger(hp);
        this.dange = dange;
    }

    public int getHp() {
        return this.hp.get();
    }

    public synchronized void takeDamage(int damage) {
        if (isAlive()) {
            String playerName = Thread.currentThread().getName();
            int actualDamage = Math.min(this.hp.get(), damage);
            this.hp.set(this.hp.get() - actualDamage);
            this.dange.getDangeResults().addDamage(playerName, actualDamage);
            String message = MessageStorage.playerDealsDamageToBoss(playerName, actualDamage);
            if (isAlive()) {
                System.out.println(message + " Boss hp = " + this.hp.get());
            } else {
                System.out.println(message + " Boss dead!");
            }
        }
    }

    public boolean isAlive() {
        return getHp() > 0;
    }
}
