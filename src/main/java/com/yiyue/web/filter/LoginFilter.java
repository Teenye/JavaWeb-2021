package com.yiyue.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        /*与登陆注册相关*/
        String[] urls={"/user/login","/imgs/","/css/","/js/","/user/register"};

        /*获取访问路径*/
        String url = request.getRequestURL().toString();
        /*找*/
        for(String u:urls){
            if(url.contains(u)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if(user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            request.getRequestDispatcher("/loginRegister.html").forward(request,servletResponse);
        }
    }
}
