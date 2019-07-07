import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Hero hero = new Hero(
                "knight",
                (byte) 10,
                500,
                65,
                38500L,
                new String[]{"high defence", "high faith"},
                Arrays.asList(
                        new Weapon("knight sword", false, (short) 120),
                        new Weapon("knight arch", true, (short) 90)
                ),
                Map.of("first", "text1text1text1", "second", "text2text2text2"),
                "pampam"
        );

//      Сериализация объекта в байты
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\hero.out"))) {
            objectOutputStream.writeObject(hero);
        }

//      Десериализация объекта из байтов
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\hero.out"))) {
            Hero hero1 = (Hero) objectInputStream.readObject();
            System.out.println("Deserialized from bytes object:\n +  " + hero1);
        }

//      Сериализация объекта в json
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("C:\\hero.json")) {
            fileWriter.write(gson.toJson(hero));
        }

//      Десериализация объекта из json
        String jsonHero = Files.toString(new File("C:\\hero.json"), Charsets.UTF_8);
        Hero hero1 = gson.fromJson(jsonHero, Hero.class);
        System.out.println("Deserialized from json object:\n +  " + hero1);


    }
}
