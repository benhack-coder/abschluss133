package ch.bbw;

import ch.bbw.entities.Person;
import ch.bbw.repository.PersonRepository;
import ch.bbw.service.ValidatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class MainController {

    private final PersonRepository repository;
    private final ValidatorService validatorService;

    public MainController(PersonRepository repository, ValidatorService validatorService) {
        this.repository = repository;
        this.validatorService = validatorService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Person> persons = (List<Person>) repository.findAll();
        model.addAttribute("persons", persons);
        return "index";
    }

    @GetMapping("/formedit")
    public String editPage(Model model, @RequestParam("id") long id) {
        Optional<Person> person = repository.findById(id);
        if (!person.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("person", person.get());
        return "form";
    }

    @GetMapping("/formcreate")
    public String create(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/submit")
    public String handleForm(Model model, @ModelAttribute Person person) {
        Map<String,String> res = validatorService.validate(person);
        if (res.get("valid").equals("false")) {
            model.addAttribute("message", res.get("msg"));
            model.addAttribute("person", person);
            return "form";
        }
        repository.save(person);
        return "redirect:/";
    }


    @GetMapping("/delete")
    public String deletePerson(Model model, @RequestParam("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
