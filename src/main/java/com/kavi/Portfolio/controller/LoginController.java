package com.kavi.Portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Make sure login.html exists in src/main/resources/templates
    }

    @PostMapping("/login")
    public RedirectView handleLogin(@RequestParam String username,
                                    @RequestParam String password,
                                    RedirectAttributes redirectAttributes) {
        if (authenticate(username, password)) {
            return new RedirectView("/home"); // Redirect to home page if successful
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return new RedirectView("/login"); // Redirect back to login page with error
        }
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Make sure home.html exists in src/main/resources/templates
    }

    private boolean authenticate(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
}
