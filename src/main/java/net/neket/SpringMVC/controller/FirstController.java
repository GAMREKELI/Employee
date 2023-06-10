package net.neket.SpringMVC.controller;

import net.neket.SpringMVC.DAO.PersonDAO;
import net.neket.SpringMVC.entity.Persone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/first")
public class FirstController {

    private final PersonDAO personDAO;

    @Autowired
    public FirstController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

//    @GetMapping("/")
//    public String getIndex(Model model) {
//        try {
//            model.addAttribute("perem", "Текст который вставляется в perem");
//            System.out.println(model.getAttribute("lenght").toString());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }

//    @GetMapping("/lol")
//    public String testLol(Model model) {
//        model.addAttribute("first", "Тут написано 'первый'");
//        model.addAttribute("second", "Тут написано 'второй'");
//        return "/testlol/lol";
//    }
//
//    @GetMapping("/")
//    public String getAll(Model model) {
//        for (int i = 0; i < personDAO.getAll().size(); i ++) {
//            System.out.println(personDAO.getAll().get(i) + "\n");
//        }
//        model.addAttribute("people", personDAO.getAll());
//        return "/people/show";
//    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "/people/show";
    }

    @GetMapping("/{id}")
    public String getPersoneAsId(@PathVariable("id") int id, Model model) {
        Persone persone = personDAO.getPersone(id);
        if (persone == null)
            return "redirect:/first";
        model.addAttribute("id", persone);
        return "/people/index";
    }

    @GetMapping("/new")
    public String personeForm(@ModelAttribute("persone") Persone persone) {
        return "/people/new";
    }

    @PostMapping()
    public String personeSubmit(@ModelAttribute("persone") Persone persone) {
        personDAO.addPersone(persone);
        return "redirect:/first";
    }
}
