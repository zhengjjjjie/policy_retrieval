package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.UserEntity;
import stackoverflow.project.policyretrieval.repository.UserRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseUtil<?> register(String username, String password) {
        if(userRepository.findByUsername(username) != null){
            return ResponseUtil.failMessage("用户名已存在！");
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setNickname(username);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseUtil.successMessage("注册成功！");
    }

    public ResponseUtil<?> login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 设置cookie
            Cookie cookie = new Cookie("user", user.getUsername());
            cookie.setHttpOnly(false);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            return ResponseUtil.success(user);
        } else {
            return ResponseUtil.fail();
        }
    }
}
