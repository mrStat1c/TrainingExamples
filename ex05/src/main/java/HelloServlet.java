import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloServlet extends HttpServlet {


    private final static String HELLO_PAGE = "hellopage.html";

    private TemplateProcessor templateProcessor = new TemplateProcessor();

    private Map<String, String[]> requestParameters = new HashMap<>();

    public HelloServlet() {
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if(requestParameters.containsKey("userName")){
            String userName = requestParameters.get("userName")[0];
            setOK(response);
            Map<String, String> substitutes = new HashMap<>();
            substitutes.put("userName", userName);
            response.getWriter().println(getHelloPage(substitutes));
        } else {
            response.sendError(400, "Parameter Name not filled!");
        }
    }

    private String getHelloPage(Map<String, String> pageVariables) throws IOException {
        return templateProcessor.getPage(HELLO_PAGE, pageVariables);
    }


    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}