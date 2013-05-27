package no.wc;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet(name = "SynchronousSubscribeServlet", urlPatterns = "/sync", loadOnStartup = 1)
public class SynchronousSubscribeServlet extends HttpServlet {

    private static LinkedList<Thread> threads = new LinkedList<>();

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("SynchronousSubscribeServlet", this);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        synchronized (threads) {
            Thread thread = Thread.currentThread();
            threads.add(thread);
        }
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {}
    }

    public void notifyClients() {
        synchronized (threads) {
            for (Thread thread : threads) {
                try {
                    thread.interrupt();
                } catch (Exception e) {}
            }
            threads.clear();
        }
    }
}

