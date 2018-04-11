package ru.ik_net.resume.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
@Component
public class ErrorFilter extends AbstractFilter {
    /**
     * Production.
     */
    @Value("${application.production}")
    private boolean production;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String requestUrl = req.getRequestURI();
        req.setAttribute("REQUEST_URL", requestUrl);
        try {
            chain.doFilter(req, resp);
        } catch (Throwable th) {
            log().error("Process request failed: " + requestUrl, th);
            handleException(th, requestUrl, resp);
        }
    }

    /**
     * @param th throwable object
     * @param requestUrl url request
     * @param resp response servlet
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    private void handleException(Throwable th, String requestUrl, HttpServletResponse resp) throws ServletException, IOException {
        if (production) {
            if ("/error".equals(requestUrl)) {
                throw new ServletException(th);
            } else {
                resp.sendRedirect("/error?url=" + requestUrl);
            }
        } else {
            throw new ServletException(th);
        }
    }
}
