package ru.ik_net.resume.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
public abstract class AbstractFilter implements Filter {
    /**
     * Log.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @return Logger
     */
    public Logger log() {
        return logger;
    }

    /** abstract do filter method.
     * @param req request
     * @param resp response
     * @param chain filter chain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException;


    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        doFilter(req, resp, chain);
    }

    @Override
    public void destroy() { }


}
