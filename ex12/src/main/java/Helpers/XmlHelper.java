package Helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class XmlHelper {

    /**
     * @param xmlContent    xml-содержимое
     * @param additionalFields поля, которые нужно добавить
     */
    public static String getXmlContentFromModifiedContent(String xmlContent, Map<String, String> additionalFields) {
        Document document;
        Transformer transformer;
        try {
            document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xmlContent.getBytes()));
            Node node = document.getFirstChild();
            additionalFields.forEach((key, value) -> {
                Element element = document.createElement(key);
                element.setTextContent(value);
                node.appendChild(element);
            });
            transformer = TransformerFactory
                    .newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            throw new RuntimeException("Error while parsing or conversion object. " + e.getMessage());
        }
    }
}
