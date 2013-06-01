package no.wc;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimeoutListener implements AsyncListener {

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        // TODO: Pehaps set status code to "408 - Timeout" instead and handle error on client side?
        asyncEvent.getSuppliedResponse().getWriter().append("timeout");
        asyncEvent.getSuppliedResponse().flushBuffer();
    }

    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
    }
}
