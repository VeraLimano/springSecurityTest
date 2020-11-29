package jm.security.example.controller;

import jm.security.example.dao.UserDao;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getUserPage(Model model) {
        //        получим всех юзеров из DAO и передадим на представление
        model.addAttribute("user", userDao.index());
        return "user";
    }

    @GetMapping(value = "/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//      получим юзера по id из DAO и передадим на представление
        model.addAttribute("user", userDao.show(id));
        return "show";
    }

    @GetMapping(value = "/user/new")
    public String newUser(@ModelAttribute("user") User user) {
//      возвращает html форму для создания нового юзера
        return "new";
    }

    @PostMapping(value = "/user")
    public String create(@ModelAttribute ("user") User user) {
//    принимать на вход post запрос, создавать нового юзера, и добавлять в БД
        userDao.save(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        return "edit";
    }

    @PatchMapping(value = "/user/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/user";
    }

//    @GetMapping(value = "/user")
//    public String index(Model model) {
////        получим всех юзеров из DAO и передадим на представление
//        model.addAttribute("users", userDao.index());
//        return "user";
//    }
}
