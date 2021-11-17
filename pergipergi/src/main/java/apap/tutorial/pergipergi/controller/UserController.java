package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.service.UserService;
import apap.tutorial.pergipergi.service.RoleService;
import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.model.RoleModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/viewall")
    public String viewAllUser(
        Model model
    ){
        model.addAttribute("listUser", userService.getListUser());
        return "viewall-user";
    }

    @GetMapping(value="/delete/{username}")
    public String deleteUser(
        @PathVariable(value = "username") String username, 
        Model model
    ){
        UserModel user = userService.findByUsername(username);
        if (user == null || username == null){
            return "no-user";
        }
        userService.deleteUser(user);
        return "delete-user";
    }

    @GetMapping("/updatePassword")
    public String updatePasswordFormPage(Model model){
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        return "form-update-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute UserModel userModel, String newPassword, String confirmPassword, Model model){
        UserModel user = userService.findByUsername(userModel.getUsername());

        if (userService.isMatch(userModel.getPassword(), user.getPassword() )){
            if (newPassword.equals(confirmPassword)){
                userService.setPassword(user, newPassword);
                userService.addUser(user);
                return "update-password";
            }else {
                return "password-mismatch";
            }
        }else {
            return "update-password-failed";
        }
    }
}
