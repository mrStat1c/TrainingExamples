import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MusicInstrumentType[] musicInstrumentTypes = MusicInstrumentType.values();
        List<MusicInstrumentType> musicInstruments = Arrays.asList(musicInstrumentTypes);

        System.out.println("toString() for each enum element:");
        musicInstruments.forEach(System.out::println);

        System.out.println("\n");
        System.out.println("getDescription() for each enum element:");
        musicInstruments.forEach(type -> System.out.println(type.getDescription()));

        System.out.println("\n");
        System.out.println("MusicInstrumentUtil.getStringCount() for each enum element:");
        musicInstruments.forEach(type -> System.out.println(MusicInstrumentUtil.getStringCount(type)));

        System.out.println("\n");
        System.out.println("MusicInstrumentUtil.isSixStringGuitar() for each enum element:");
        musicInstruments.forEach(type -> System.out.println(MusicInstrumentUtil.isSixStringGuitar(type)));

        System.out.println("\n");
        System.out.println("isElectroInstrument() for each enum element:");
        musicInstruments.forEach(type -> System.out.println(type.isElectroInstrument()));

    }
}
