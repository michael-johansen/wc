package no.wc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: MicJoh
 * Date: 07.04.13
 * Time: 11:43
 */
@WebServlet(name = "NotifyServlet", urlPatterns = "notify", asyncSupported = true)
public class NotifyServlet extends HttpServlet {

    private static List<Thread> threadList = new ArrayList<Thread>();

    public NotifyServlet() {
        System.out.println("created");
    }

    public static void eventHappened() {
        synchronized (threadList){
            System.out.println("waking threads");
            for (Thread thread : threadList) {
                System.out.println("waking thread: " + thread.getName());

                thread.interrupt();
            }
            threadList.clear();
        }
    }

    @Override
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        final AsyncContext asyncContext = servletRequest.startAsync();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                addThread();
                sleep();
                writeResponse(asyncContext);
                asyncContext.complete();
            }
        });
    }

    private void sleep() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            System.out.println("Thread waked");
        }
    }

    private void writeResponse(AsyncContext asyncContext) {
        try {
            final ServletResponse response = asyncContext.getResponse();
            response.getWriter().append(String.format("event @ %s", new Date()));
            response.flushBuffer();
        } catch (IOException e) {}
    }

    private void addThread() {
        System.out.println("trying to add thread");
        synchronized (threadList){
            threadList.add(Thread.currentThread());
            System.out.println("added thread");
        }
    }
}
