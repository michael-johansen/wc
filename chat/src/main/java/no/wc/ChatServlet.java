package no.wc;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static no.wc.MessagesListener.MESSAGES;
import static no.wc.UserNameServlet.USER;

@WebServlet(urlPatterns = "/chat", loadOnStartup = 2)
public class ChatServlet extends HttpServlet {

    public static final String TEXT = "text";
    public static final String SYSTEM_USER = "Chatter";

    public ChatServlet() {
        System.out.println("new chat servlet!");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        addUserIfNew(httpServletRequest);

        httpServletRequest.setAttribute(MESSAGES, MessagesListener.getMessages(getServletContext()));
        httpServletRequest.setAttribute(USER, httpServletRequest.getSession().getAttribute(USER));

        httpServletRequest.getRequestDispatcher("/WEB-INF/chat.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String user = UserNameServlet.loadUserName(httpServletRequest);
        String text = httpServletRequest.getParameter(TEXT);

        if (hasContent(text)) {
            MessagesListener.addMessage(user, text, getServletContext());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
    }

    private boolean hasContent(String text) {
        return text != null && !text.isEmpty();
    }

    private void addUserIfNew(HttpServletRequest httpServletRequest) {
        if (userIsNew(httpServletRequest)) {
            String text = format("welcome user @%s", httpServletRequest.getSession().getId());
            MessagesListener.addMessage(SYSTEM_USER, text, getServletContext());
        }
    }

    private boolean userIsNew(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession(false) == null;
    }
}
