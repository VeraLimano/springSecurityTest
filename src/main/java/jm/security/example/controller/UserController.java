package jm.security.example.controller;

import jm.security.example.dao.UserDao;
import jm.security.example.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String printWelcome(ModelMap model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userDao.getUserByName(name);
        model.addAttribute("username", user);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String getAdminPage(Model model) {
        //        получим всех юзеров из DAO и передадим на представление
        model.addAttribute("user", userDao.index());
        return "admin";
    }

    @GetMapping(value = "/admin/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//      получим юзера по id из DAO и передадим на представление
        model.addAttribute("user", userDao.show(id));
        return "show";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
//      возвращает html форму для создания нового юзера
        return "new";
    }

    @PostMapping(value = "/admin")
    public String create(@ModelAttribute ("user") User user) {
//    принимать на вход post запрос, создавать нового юзера, и добавлять в БД
        userDao.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        return "edit";
    }

    @PatchMapping(value = "/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/user")
//    public String index(Model model) {
////        получим всех юзеров из DAO и передадим на представление
//        model.addAttribute("users", userDao.index());
//        return "user";
//    }
}
