package biz.bagira.shds.eshop.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Dmitriy on 21.10.2016.
 */
public class LogInFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession();
//        Object user = session.getAttribute("user");

    }

    public void destroy() {

    }
}
