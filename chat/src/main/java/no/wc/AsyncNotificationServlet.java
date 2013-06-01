package no.wc;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet(name = "AsyncNotificationServlet", urlPatterns = "/notify", asyncSupported = true, loadOnStartup = 1)
public class AsyncNotificationServlet extends HttpServlet {

    private LinkedList<AsyncContext> asyncContexts = new LinkedList<>();

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("AsyncNotificationServlet", this);
    }

    @Override
    protected synchronized void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AsyncContext asyncContext = httpServletRequest.startAsync();
        synchronized (asyncContexts) {
            asyncContexts.add(asyncContext);
            asyncContext.addListener(new TimeoutListener());
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

