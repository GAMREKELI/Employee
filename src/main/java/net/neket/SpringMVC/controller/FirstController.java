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

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "/people/show";
    }

    @GetMapping("/{id}")
    public String getPersonAsId(@PathVariable("id") int id, Model model) {
        Persone persone = personDAO.getPersone(id);
        if (persone == null)
            return "redirect:/first";
        model.addAttribute("id", persone);
        return "/people/index";
    }

    @GetMapping("/new")
    public String addPerson(@ModelAttribute("person") Persone persone) {
        return "/people/new";
    }

    @PostMapping()
    public String personSubmit(@ModelAttribute("person") Persone persone) {
        personDAO.addPersone(persone);
        return "redirect:/first";
    }
    @GetMapping("/{id}/update")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersone(id));
        return "/people/update";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Persone persone) {
        personDAO.update(persone, id);
        return "redirect:/first";
    }
//
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.deletePersone(id);
        return "redirect:/first";

    }
}
