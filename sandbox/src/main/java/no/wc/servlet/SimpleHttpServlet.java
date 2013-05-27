package no.wc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * User: MicJoh
 * Date: 17.03.13
 * Time: 18:31
 */
@WebServlet(name = "SimpleHttpServlet", urlPatterns = "/simple")
public class SimpleHttpServlet extends HttpServlet {

    public SimpleHttpServlet() {
        System.out.printf("%s:new()%n", getClass().getSimpleName());
    }

    @Override
    public void init() throws ServletException {
        System.out.printf("%s:init()%n", getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet handling request");

        PrintWriter writer = resp.getWriter();
        for (Map.Entry<String, String[]> stringEntry : req.getParameterMap().entrySet()) {
            writer.println(stringEntry.getKey() + ": " + Arrays.toString(stringEntry.getValue()));
        }

        NotifyServlet.eventHappened();
    }
}
