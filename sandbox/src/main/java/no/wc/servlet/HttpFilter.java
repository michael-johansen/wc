package no.wc.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: MicJoh
 * Date: 24.03.13
 * Time: 16:26
 */

public abstract class HttpFilter implements Filter {

    protected HttpFilter() {
        System.out.printf("%s:new()%n", getClass().getSimpleName());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.printf("%s:init()%n", getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {
        System.out.printf("%s:destroy()%n", getClass().getSimpleName());
    }
}
