import Helpers.JsonHelper;
import Helpers.XmlHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        TestObject testObject = new TestObject();
        testObject.setTestStringField("Praise the sun!");
        testObject.setTestIntField(12);
        testObject.setTestBooleanField(true);
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        testObject.setTestListField(list);

        Map<String, String> fields = new HashMap<>();
        fields.put("firstField", "123");
        fields.put("secondField", "456");
        String jsonContent = JsonHelper.getJsonContentFromModifiedContent(testObject, fields);
        System.out.println(jsonContent);

        String xmlContent = "<TestObject>" +
                "<x>1</x>" +
                "<y>2</y>" +
                "<z>3</z>" +
                "</TestObject>";
        String modifiedXmlContent = XmlHelper.getXmlContentFromModifiedContent(xmlContent, fields);
        System.out.println(modifiedXmlContent);
    }
}
