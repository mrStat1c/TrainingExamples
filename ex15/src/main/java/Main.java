import com.google.gson.Gson;
import model.Client;

public class Main {


    public static void main(String[] args) {

        Client client = new Client();
        //формируем клиента

        //сериализуем в String
        Gson gson = new Gson();
        String json = gson.toJson(client);

        //десериализуем в Client
    }
}
