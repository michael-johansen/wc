package no.wc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static no.wc.ChatServlet.SYSTEM_USER;

@WebServlet(urlPatterns = "/user")
public class UserNameServlet extends HttpServlet {

    public static final String USER = "user";

    public static String loadUserName(HttpServletRequest httpServletRequest) {
        Object user = httpServletRequest.getSession().getAttribute(USER);
        String id = httpServletRequest.getSession().getId();
        return asStringOrDefault(user, id);
    }

    private static String asStringOrDefault(Object parameter, String defaultValue) {
        return parameter == null ? defaultValue : parameter.toString();
    }

    private static void saveUserName(HttpServletRequest httpServletRequest, String newUserName) {
        httpServletRequest.getSession().setAttribute(USER, newUserName);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String oldUserName = loadUserName(httpServletRequest);
        String newUserName = httpServletRequest.getParameter(USER);
        if (!oldUserName.equals(newUserName)) {
            saveUserName(httpServletRequest, newUserName);
            MessagesListener.addMessage(SYSTEM_USER, oldUserName + " changes name to " + newUserName, getServletContext());
        }
    }


}
