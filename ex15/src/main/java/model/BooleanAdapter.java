package model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BooleanAdapter extends TypeAdapter<Boolean> {



    @Override
    @SuppressWarnings("unchecked")
    public Boolean read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        if (token == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        if (token != JsonToken.BOOLEAN) {
            String warnMsg = String.format("\nField \"%s\" has no boolean value!\n", in.getPath().replace("$.", ""));
            System.out.println(warnMsg);
        }
        // если для boolean поля приходит String значение, конвертируем значение в boolean
        return Boolean.parseBoolean(in.nextString());
    }

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        out.value(value);
    }
}
