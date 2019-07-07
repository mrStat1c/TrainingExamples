import java.io.Serializable;

public class Weapon implements Serializable{

    private String name;
    private boolean remote;
    private short damage;

    public Weapon(String name, boolean remote, short damage) {
        this.name = name;
        this.remote = remote;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", remote=" + remote +
                ", damage=" + damage +
                '}';
    }
}