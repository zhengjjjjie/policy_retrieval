package stackoverflow.project.policyretrieval.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import stackoverflow.project.policyretrieval.entity.UserEntity;
import stackoverflow.project.policyretrieval.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        if (url.equals("/error")){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"msg\":\"/error\"}");
            return false;
        }
        // 不需要登录的url放行
        if (!needAuth(url)) {
            return true;
        }

        // 需要登录的url进行拦截
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            // 用户未登录，跳转到登录页面
//            response.sendRedirect(request.getContextPath() + "/api/auth/login");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"用户未登录\"}");
            return false;
        }

        // 登录用户的信息持久化
        session.setAttribute("user", user);

        // 判断用户是否有权限访问该url
        if (!hasPermission(user, url)) {
            // 没有权限，跳转到403页面
            response.setStatus(HttpStatus.FORBIDDEN.value());
            request.getRequestDispatcher("/error/403").forward(request, response);
            return false;
        }

        return true;
    }

    private boolean needAuth(String url) {
        // 判断url是否需要进行权限验证
        // 此处可以根据具体业务进行修改
        return !(url.startsWith("/api/auth") ||
                url.startsWith("/api/policy/search"));
    }

    private boolean hasPermission(UserEntity user, String url) {
        // 判断用户是否有权限访问该url
        // 此处可以根据具体业务进行修改
        if (user.getRole().equals("admin")) {
            return true;
        } else if (user.getRole().equals("enquirer")) {
            return (url.startsWith("/api/enquirer"));
        }
            return false;
        }
    }