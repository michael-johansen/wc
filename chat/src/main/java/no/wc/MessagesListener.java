package no.wc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class MessagesListener implements ServletContextAttributeListener {
    public static final String MESSAGES = "messages";

    public static void addMessage(String user, String text, ServletContext servletContext) {
        List<Message> messages = getMessages(servletContext);
        messages.add(new Message(user, text));
        servletContext.setAttribute("messages", messages);
    }

    public static List<Message> getMessages(ServletContext servletContext) {
        List<Message> messages = (List<Message>) servletContext.getAttribute("messages");
        if (messages == null){
            messages = new ArrayList<>();
            servletContext.setAttribute("messages", messages);
            return messages;
        }
        return messages;
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        handleEvent(servletContextAttributeEvent);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        handleEvent(servletContextAttributeEvent);
    }

    private void handleEvent(ServletContextAttributeEvent servletContextAttributeEvent) {
        if (servletContextAttributeEvent.getName().equals("messages")) {
            ServletContext servletContext = servletContextAttributeEvent.getServletContext();

            SubscribeServlet async = (SubscribeServlet) servletContext.getAttribute("SubscribeServlet");
            async.notifyClients();

            SynchronousSubscribeServlet sync = (SynchronousSubscribeServlet) servletContext.getAttribute("SynchronousSubscribeServlet");
            sync.notifyClients();
        }
    }
}
