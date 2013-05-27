package no.wc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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
@WebServlet(urlPatterns = "/app/*", loadOnStartup = 0 )
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("main")) {
            req.getRequestDispatcher("/WEB-INF/jsp" + req.getPathInfo() + ".jsp").forward(req, resp);
        } else {
        }
    }


}
