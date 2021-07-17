package app.Controllers;
import app.Entities.User;
import app.JpaRepository.UserRepository;
import app.Vo.LoginForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginForm login) {
        User user = userRepository.findByUsername(login.getUsername());
        Map<String, Object> map = new HashMap<>(3);
        //System.out.println("----------"+encoder.encode(login.getPassword())+"--------------");
        if (user == null) {
            map.put("status", "fail");
            map.put("msg", "Account does not exist.");
        } else if (!encoder.matches(login.getPassword(),user.getPassword())) {
            map.put("status", "fail");
            map.put("msg", "Wrong username or password.");
        } else {
            user = userRepository.findByUsername(login.getUsername());
            map.put("status", "success");
            map.put("data", user);
        }
        return map;
    }
}