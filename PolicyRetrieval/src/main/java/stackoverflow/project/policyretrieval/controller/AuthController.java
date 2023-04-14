package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.UserEntity;
import stackoverflow.project.policyretrieval.service.UserService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginRequestView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseUtil<?> login(@RequestBody LoginRequestView loginRequestView,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        return userService.login(loginRequestView.getUsername(), loginRequestView.getPassword(), request, response);

    }
    @PostMapping("/logout")
    public ResponseUtil<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseUtil.success();
    }
    @PostMapping("/register")
    public ResponseUtil<?> register(@RequestBody LoginRequestView loginRequestView) {
        return userService.register(loginRequestView.getUsername(), loginRequestView.getPassword());
    }
}
