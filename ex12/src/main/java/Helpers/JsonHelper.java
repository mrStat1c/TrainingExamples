package Helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class JsonHelper {

    /**
     * @param object    объект, в который нужно добавить поля, отсутствующие в его модели
     * @param additionalFields поля, которые нужно добавить
     * @return json-представление модифицированного запроса
     */
    public static String getJsonContentFromModifiedContent(Object object, Map<String, String> additionalFields) {
        JsonObject jsonObject = new Gson().toJsonTree(object).getAsJsonObject();
        additionalFields.forEach(jsonObject::addProperty);
        return jsonObject.toString();
    }



}
