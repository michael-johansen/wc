package no.wc.servlet;
/**
 * User: MicJoh
 * Date: 24.03.13
 * Time: 16:44
 */

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"SimpleHttpServlet"})
public class LogFilter extends HttpFilter {
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.printf("%s %s [%s]%n", request.getMethod(), request.getServletPath(), request.getQueryString());
        filterChain.doFilter(request, response);
        System.out.printf("StatusCode: %d%n", response.getStatus());
    }
}
