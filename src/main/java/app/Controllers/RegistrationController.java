package app.Controllers;

import app.Entities.User;
import app.JpaRepository.UserRepository;
import app.Vo.RegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @ResponseBody
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterForm regUser) {
        System.out.println(regUser);
        User user = userRepository.findByUsername(regUser.getUsername());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>(3);
        if (user == null) {
            user = new User();
            user.setUsername(regUser.getUsername());
            user.setPassword(encoder.encode(regUser.getPassword()));
            user.setType(regUser.getType());
            user.setSex(regUser.getSex());
            user.setPhone(regUser.getPhone());
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            user.setEmail(regUser.getEmail());
            user.setName(regUser.getName());
            userRepository.save(user);
            User saveSuccess = userRepository.findByUsername(user.getUsername());
            System.out.println("------------Running------------");
            if (saveSuccess == null) {
                map.put("status", "fail");
                map.put("msg", "User registration failed.");
            } else {
                map.put("status", "success");
                map.put("data", saveSuccess);
            }
        } else {
            map.put("status", "fail");
            map.put("msg", "The username has been registered.");
        }
        return map;
    }
}

