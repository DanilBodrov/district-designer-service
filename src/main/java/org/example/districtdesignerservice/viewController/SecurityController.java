package org.example.districtdesignerservice.viewController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.districtdesignerservice.model.User;
import org.example.districtdesignerservice.service.UserService;
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

    //главная страница
    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/index";
    }

    // страница регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "register";
    }

    @PostMapping("/processRegister")
    public String processRegistration(@ModelAttribute User user) {
        boolean isExistUser = userService.crateUser(user);
        if (isExistUser) {
            return "redirect:/register?fail=true";
        }
        return "redirect:/login";
    }

    //страница авторизации
    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@ModelAttribute User user, HttpSession session) {
        User findUser = userService.getUser(user.getEmail(), user.getPassword());

        if (findUser != null) {
            session.setAttribute("user", findUser);
            return "redirect:/index";
        }
        return "redirect:/login?fail=true";
    }

    //выход из учётной записи
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
