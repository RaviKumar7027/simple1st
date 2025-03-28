
package com.example.JournalApplication.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login.html"; // ✅ Static file ko redirect karega
    }
    @GetMapping("/h")
    public String hii(){
        return "hello ravi";
    }

}
