package org.example.districtdesignerservice.viewController;

import lombok.RequiredArgsConstructor;
import org.example.districtdesignerservice.model.User;
import org.example.districtdesignerservice.service.UserService;
import org.springframework.boot.autoconfigure.websocket.servlet.TomcatWebSocketServletWebServerCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final TomcatWebSocketServletWebServerCustomizer tomcatWebSocketServletWebServerCustomizer;

    @GetMapping("/index")
    public String index(Model model) {
        return "/index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "register"; // Отображаем форму регистрации register.html
    }

    @PostMapping("/processRegister")
    public String processRegistration(@ModelAttribute User user) {
        boolean isExistUser = userService.registerUser(user);
        if (isExistUser) {
            return "redirect:/register?fail=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@ModelAttribute User user) {
        boolean isExistUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (isExistUser) {
            return "redirect:/index";
        }
        return "redirect:/login?fail=true";
    }
}
