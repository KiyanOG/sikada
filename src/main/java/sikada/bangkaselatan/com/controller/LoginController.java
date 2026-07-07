package sikada.bangkaselatan.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sikada.bangkaselatan.com.entity.Admin;
import sikada.bangkaselatan.com.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    public LoginController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        Admin admin =
                adminRepository.findByUsername(username);

        if(admin != null &&
           admin.getPassword().equals(password)) {

            session.setAttribute("admin", admin);

            return "redirect:/admin/dashboard";
        }

        return "redirect:/admin/login?error";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session){

        session.invalidate();

        return "redirect:/admin/login";
    }

    public AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

}