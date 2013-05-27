package no.wc;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet(name = "SubscribeServlet", urlPatterns = "/notify", asyncSupported = true, loadOnStartup = 1)
public class SubscribeServlet extends HttpServlet {

    private LinkedList<AsyncContext> asyncContexts = new LinkedList<>();

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("SubscribeServlet", this);
    }

    @Override
    protected synchronized void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AsyncContext asyncContext = httpServletRequest.startAsync();
        synchronized (asyncContexts) {
            asyncContexts.add(asyncContext);
        }
    }

    public void notifyClients() {
        synchronized (asyncContexts) {
            for (AsyncContext asyncContext : asyncContexts) {
                try {
                    asyncContext.complete();
                } catch (IllegalStateException e) {}
            }
            asyncContexts.clear();
        }
    }
}

