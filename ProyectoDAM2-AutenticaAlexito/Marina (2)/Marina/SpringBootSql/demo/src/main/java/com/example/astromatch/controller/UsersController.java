package com.example.astromatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.astromatch.model.UsersModel;
import com.example.astromatch.service.UsersService;

@Controller
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("RegisterRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("LoginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) {
        System.out.println("Register request" + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getNombre(), usersModel.getApellido(),usersModel.getEdad(), usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("Login request" + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_page";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/inicio")
    public String index(@ModelAttribute UsersModel usersModel, Model model) {
        return "inicio";
    }

    @GetMapping("/aries")
    public String getAriesPage() {
        return "Aries.html"; // nombre del archivo HTML sin la extensión .html
    }

    @GetMapping("/tauro")
    public String getTauroPage() {
        return "Tauro.html"; // nombre del archivo HTML de tauro
    }

}
