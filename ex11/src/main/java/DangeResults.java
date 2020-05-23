import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class DangeResults {

    private Map<String, Statistic> results = new HashMap<>();

    @AllArgsConstructor
    private class Statistic {
        int totalDamage;
        int lootedChestsCount;
    }

    public synchronized void addDamage(String playerName, int damage) {
        if (results.containsKey(playerName)) {
            results.get(playerName).totalDamage += damage;
        } else {
            results.put(playerName, new Statistic(damage, 0));
        }
    }

    public synchronized void addLootedChest(String playerName) {
        if (results.containsKey(playerName)) {
            results.get(playerName).lootedChestsCount++;
        } else {
            results.put(playerName, new Statistic(0, 1));
        }
    }

    public void announce() {
        System.out.println("Dange Results:");
        results.forEach((player, statistic) ->
                System.out.println(MessageStorage.playerStatistic(
                        player,
                        statistic.totalDamage,
                        statistic.lootedChestsCount)
                )
        );
    }
}
