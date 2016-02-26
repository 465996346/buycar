package com.suminghui.bycar.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suminghui.bycar.bean.User;
import com.suminghui.bycar.common.AppContext;
import com.suminghui.bycar.common.Constants;

public class AppContextFilter implements Filter {

    public AppContextFilter() {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域访问问题
        AppContext.setContextPath(request.getContextPath());

        AppContext appContext = AppContext.getContext();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Constants.USER);
        if (null != user) {
            appContext.addObject(Constants.APP_CONTEXT_USER, user);
        }

        // 获取请求参数并存放到parameters中
        Map<String, String> parameters = new HashMap<String, String>();
        Map<String, String[]> parameterInRequest = request.getParameterMap();

        for (String skey : parameterInRequest.keySet()) {
            String _value = parameterInRequest.get(skey)[0];
            parameters.put(skey.toLowerCase(), _value);
            parameters.put(skey.toUpperCase(), _value);
            parameters.put(skey, _value);
        }

        appContext.addObject(Constants.APP_CONTEXT_PARAMETER, parameters);

        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);

        try {
            filterChain.doFilter(request, response);
        } finally {
            appContext.clear();
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
