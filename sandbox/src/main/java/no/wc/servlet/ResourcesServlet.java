package no.wc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: MicJoh
 * Date: 27.03.13
 * Time: 13:30
 */
@WebServlet(name = ResourcesServlet.RESOURCE_SERVLET, urlPatterns = "/resources/**", loadOnStartup = 1)
public class ResourcesServlet extends HttpServlet {

    public static final String RESOURCE_SERVLET = "resourceServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources" + req.getPathInfo()).include(req, resp);

    }
}
