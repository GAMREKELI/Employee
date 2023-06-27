package ru.pdursley.Employee.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pdursley.Employee.DAO.PersonDAO;
import ru.pdursley.Employee.model.Person;

@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    private final PersonDAO personDAO;

    @Autowired
    public EmployeeController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "/people/show";
    }

//    @GetMapping("/{id}")
//    public String getPersonAsId(@PathVariable("id") int id, Model model) {
//        Person person = personDAO.getPerson(id);
//        if (person == null)
//            return "redirect:/Employee";
//        model.addAttribute("id", person);
//        return "/people/index";
//    }
//
//    @GetMapping("/new")
//    public String addPerson(@ModelAttribute("person") Person person) {
//        return "/people/new";
//    }
//
//    @PostMapping()
//    public String personSubmit(@ModelAttribute("person") @Valid Person person,
//                               BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "/people/new";
//        personDAO.addPerson(person);
//        return "redirect:/Employee";
//    }
//    @GetMapping("/{id}/update")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("person", personDAO.getPerson(id));
//        return "/people/update";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "/people/update";
//        personDAO.update(person, id);
//        return "redirect:/Employee";
//    }
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        personDAO.deletePerson(id);
//        return "redirect:/Employee";
//
//    }
}
