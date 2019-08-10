import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.DataSets.UserDataSet;
import ru.otus.DbService.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class UserServlet extends HttpServlet {

    private final static String PARAMETER_NAME = "name";
    private final static String PARAMETER_AGE = "age";
    private final static String PARAMETER_REQUEST_TYPE = "requestType";
    private final static String PARAMETER_ID = "id";
    private final static String ADMIN_PAGE_NAME = "admin.html";

    @Autowired
    private DbService dbService;

    @Autowired
    private TemplateProcessor templateProcessor;

    private Map<String, String[]> requestParameters = new HashMap<>();

    public UserServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if (requestParameters.containsKey(PARAMETER_NAME)
                && requestParameters.containsKey(PARAMETER_AGE)) {
            String userName = requestParameters.get(PARAMETER_NAME)[0];
            String userAge = requestParameters.get(PARAMETER_AGE)[0];
            if (!userName.isEmpty() && !userAge.isEmpty()) {
                UserDataSet user = new UserDataSet();
                user.setName(userName);
                user.setAge(Integer.parseInt(userAge));
                dbService.save(user);
                setOK(response);
                response.getWriter().println(getAdminPage(new HashMap<>()));
            } else {
                response.sendError(400, "Parameter Name or Age not filled!");
            }
        } else {
            response.sendError(400, "Parameter Name or Age not sended!");
        }

    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if (requestParameters.containsKey(PARAMETER_REQUEST_TYPE)) {
            if (requestParameters.get(PARAMETER_REQUEST_TYPE)[0].equals("nameById")) {
                if (requestParameters.containsKey(PARAMETER_ID)) {
                    String id = requestParameters.get(PARAMETER_ID)[0];
                    if (!id.isEmpty()) {
                        UserDataSet userDataSet = dbService.load(UserDataSet.class, Integer.parseInt(requestParameters.get("id")[0]));
                        setOK(response);
                        String userName;
                        try {
                            userName = userDataSet.getName();
                        } catch (LazyInitializationException e) {
                            userName = "User not found!";
                        }
                        response.getWriter().println(getAdminPage(Map.of("userName", userName)));
                    } else {
                        response.sendError(400, "Parameter id not filled!");
                    }
                }
            } else if (requestParameters.get(PARAMETER_REQUEST_TYPE)[0].equals("usersCount")) {
                setOK(response);
                response.getWriter().println(getAdminPage(Map.of("userCount",
                        String.valueOf(dbService.getRecordCount("UserDataSet")))));
            }
        } else {
            setOK(response);
            response.getWriter().println(getAdminPage(new HashMap<>()));
        }
    }

    private String getAdminPage(Map<String, String> pageVariables) throws IOException {
        return templateProcessor.getPage(ADMIN_PAGE_NAME, pageVariables);
    }


    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}