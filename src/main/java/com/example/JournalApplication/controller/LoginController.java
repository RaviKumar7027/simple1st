//
//package com.example.JournalApplication.controller;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class LoginController {
//
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "redirect:/login.html"; // ✅ Static file ko redirect karega
//    }
//    @GetMapping("/h")
//    @ResponseBody
//    public String hii(){
//        return "hello ravi";
//    }
//
//}

package com.example.JournalApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // ✅ Thymeleaf ya Static HTML Render Karega
    }

    @GetMapping("/p")
    @ResponseBody // ✅ String response return karega bina kisi View ke
    public String hii() {
        return "hello ravi";
    }
}
