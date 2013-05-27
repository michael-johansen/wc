package no.wc;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * User: Michael Johansen
 * Date: 09.05.13
 * Time: 01:19
 */
@WebListener
public class MessagesListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
       if(servletContextAttributeEvent.getName().equals("messages")){
           System.out.println("!");
       }
    }
}
