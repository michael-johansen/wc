package no.wc;

import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet(urlPatterns = "/notify", asyncSupported = true, loadOnStartup = 1)
public class SubscribeServlet extends HttpServlet {

    @Inject
    private AsyncNotifyListener asyncNotifyListener;

    @Override
    protected synchronized void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        asyncNotifyListener.addContext(httpServletRequest.startAsync());
    }


}

