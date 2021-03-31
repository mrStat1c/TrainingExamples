import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        Client client = new Client();
        Address address_1 = new Address();
        address_1.setCity("Moscow");
        address_1.setStreet("Super street");
        address_1.setBuilding(14);
        address_1.setFlat(256);
        Address address_2 = new Address();
        address_2.setCity("London");
        address_2.setStreet("Mega street");
        address_2.setBuilding(888);
        address_2.setFlat(31);
        Phone phone = new Phone();
        phone.setMobile("+79991112233");
        phone.setHome("+7495999777");
        client.setAddresses(Arrays.asList(address_1, address_2));
        client.setPhone(phone);
        client.setFirstName("Mr");
        client.setLastName("Static");
        client.setAge((byte) 30);
        client.setGender(Gender.M);
        client.setGoodMan(true);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
//                .registerTypeAdapter(Boolean.class, new StrictBooleanTypeAdapter()) //если нужно включить адаптер для всех полей
                .create();

        String jsonClient = gson.toJson(client);
        System.out.println("Client json view:\n" + jsonClient);

        // заменяем boolean значение на String
        String modifiedJsonClient = jsonClient.replace("\"good_man\": true", "\"good_man\": \"true\"");
        client = gson.fromJson(modifiedJsonClient, Client.class);
        System.out.println("Client json view:\n" + gson.toJson(client));
    }
}
