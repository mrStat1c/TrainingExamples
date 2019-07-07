import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hero implements Serializable{

    private String specialization;
    private byte level;
    private int hp;
    private int mana;
    private long gold;
    private String[] features;
    private List<Weapon> weapons;
    private Map<String, String> notes;
    private transient String secretWord;

    public Hero(
            String specialization,
            byte level,
            int hp,
            int mana,
            long gold,
            String[] features,
            List<Weapon> weapons,
            Map<String, String> notes,
            String secretWord) {
        this.specialization = specialization;
        this.level = level;
        this.hp = hp;
        this.mana = mana;
        this.gold = gold;
        this.features = features;
        this.weapons = weapons;
        this.notes = notes;
        this.secretWord = secretWord;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "specialization='" + specialization + '\'' +
                ", level=" + level +
                ", hp=" + hp +
                ", mana=" + mana +
                ", gold=" + gold +
                ", features=" + Arrays.toString(features) +
                ", weapons=" + weapons +
                ", notes=" + notes +
                ", secretWord='" + secretWord + '\'' +
                '}';
    }
}