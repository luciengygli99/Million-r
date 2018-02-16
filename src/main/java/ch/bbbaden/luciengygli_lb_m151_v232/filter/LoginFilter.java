package ch.bbbaden.luciengygli_lb_m151_v232.filter;

import ch.bbbaden.luciengygli_lb_m151_v232.controller.LoginController;
import ch.bbbaden.luciengygli_lb_m151_v232.model.LoggerHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }
    private static final Logger LOG = Logger.getLogger(LoginFilter.class.getName());

    @Inject
    private LoginController loginController;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String loginURL = httpRequest.getContextPath() + "/index.xhtml";

        if (!loginController.isLoggedIn()) {
            LOG.log(Level.INFO, "LucienGygli_LB_M151_V232 to load protected page {0} from IP:{1}, user agent:{2}, redirected to login.", new Object[]{httpRequest.getRequestURL(), LoggerHelper.getRemoteAddr(httpRequest), LoggerHelper.getUserAgent(httpRequest)});
            httpResponse.sendRedirect(loginURL);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
