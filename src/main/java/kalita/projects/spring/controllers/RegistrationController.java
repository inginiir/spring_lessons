package kalita.projects.spring.controllers;

import kalita.projects.spring.domain.Role;
import kalita.projects.spring.domain.User;
import kalita.projects.spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController  {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userRepo .findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.put("message", "User already exist");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
