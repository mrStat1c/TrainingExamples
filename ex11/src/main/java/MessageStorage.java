public class MessageStorage {

    public static String playerIsReady(){
       return "Player \"" + Thread.currentThread().getName() + "\" ready to go to dange!";
    }

    public static String playerWentToDange(){
        return "Player \"" + Thread.currentThread().getName() + "\" went to dange!";
    }

    public static String playerDealsDamageToBoss(String playerName, int damage){
        return "Player \"" + playerName + "\" deals " + damage + " damage to Boss!";
    }

    public static String playerLootedChest(String playerName, int lootTime){
        return "Player \"" + playerName + "\" looted chest in the " + lootTime + " second";
    }

    public static String playerStatistic(String playerName, int totalDamage, int lootedChestsCount){
        return String.format("Player %s deals %s total damage to Boss and looted %s chests!",
                playerName, totalDamage, lootedChestsCount);
    }
}
