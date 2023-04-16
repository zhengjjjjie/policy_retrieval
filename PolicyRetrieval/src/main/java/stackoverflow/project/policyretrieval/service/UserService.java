package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.UserEntity;
import stackoverflow.project.policyretrieval.repository.UserRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.UserRegisterView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseUtil<?> register(UserRegisterView user) {
        if (user == null) {
            return ResponseUtil.failMessage("user 不能为空！");
        }

        if (user.getUsername() == null || user.getUsername().equals("")) {
            return ResponseUtil.failMessage("请输入用户名！");
        }
        if( userRepository.existsByUsername(user.getUsername())){
            return ResponseUtil.failMessage("用户名已存在！");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            return ResponseUtil.failMessage("请输入密码！");
        }
        if (user.getAge() == null) {
            return ResponseUtil.failMessage("请输入年龄！");
        } else if (user.getAge() >= 100 || user.getAge() <= 0) {
            return ResponseUtil.failMessage("年龄太大！");
        }
        if ((! user.getRole().equals("admin") )&& (! user.getRole().equals("enquirer")) ) {
            return ResponseUtil.failMessage("role必须为admin 或 enquirer");
        }
        EnquirerEntity enquirerEntity = new EnquirerEntity();
        BeanUtils.copyProperties(user, enquirerEntity);

        userRepository.save(enquirerEntity);
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
