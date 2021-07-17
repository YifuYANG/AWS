package app.Controllers;
import app.Entities.User;
import app.JpaRepository.UserRepository;
import app.Vo.LoginForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {

    UserRepository userRepository;

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginForm login) {
        User user = userRepository.findByUsername(login.getUsername());
        Map<String, Object> map = new HashMap<>(3);

        if (user == null) {
            map.put("status", "fail");
            map.put("msg", "Account does not exist.");
        } else if (!user.getPassword().equals(login.getPassword())) {
            map.put("status", "fail");
            map.put("msg", "Wrong username or password.");
        } else {
            userRepository.save(user);
            user = userRepository.findByUsername(login.getUsername());
            map.put("status", "success");
            map.put("data", user);
        }
        return map;
    }
}

