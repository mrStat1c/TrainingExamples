import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateProcessor {
    private static final String HTML_DIR = "/pageTemplates/";
    private static Map<String, String> pageVariables = new HashMap<>();
    static {
        pageVariables.put("userName", "");
        pageVariables.put("userAge", "");
        pageVariables.put("userCount", "");
    }
    private final Configuration configuration;

    public TemplateProcessor() {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), HTML_DIR);
        configuration.setDefaultEncoding("UTF-8");
    }

    String getPage(String filename, Map<String, String> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            pageVariables.putAll(data);
            template.process(pageVariables, stream);
            return stream.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }
}