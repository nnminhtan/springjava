package com.example.demo.Controller;


import com.example.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private static List<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person("Tan", "Nguyen"));
        persons.add(new Person("Khoa", "Ngo"));
        persons.add(new Person("An", "Truong"));
        persons.add(new Person("Bang", "Le"));
        persons.add(new Person("Luong", "Chau"));
        persons.add(new Person("Dat", "Luu"));
    }


    @GetMapping("/personList")
    public String personList(Model model) {
        model.addAttribute("persons", persons);
        return "List-person";
    }
    @GetMapping("/addPerson")
    public String showAddPersonForm(Model model) {
        Person personForm=new Person();
        model.addAttribute("personForm",personForm);
        return "addPerson";
    }
    @RequestMapping(value=("/addPerson"),method = RequestMethod.POST)
    public String savePerson(Model model,@ModelAttribute("personForm") Person personForm){
        String firstName=personForm.getFirstName();
        String lastName=personForm.getLastName();
        if(firstName!=null &&firstName.length()>0&&lastName!=null&&lastName.length()>0){
            //add to list
            persons.add(personForm);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage" ,"FirstName & LastName is required");
        return"addPerson";
    }
    @GetMapping("/{param}")
    public String welcome(@PathVariable String param, Model model) {
        model.addAttribute("message", "hello " + param);
        return "index";
    }

}

