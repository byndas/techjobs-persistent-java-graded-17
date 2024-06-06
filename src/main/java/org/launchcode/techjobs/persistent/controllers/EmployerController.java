package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;
//    public EmployerController(EmployerRepository employerRepository) {
//        this.employerRepository = employerRepository;
//    } // @Autowired prevents needing to write this?

    @GetMapping(value="/") // tests & routing require (value="/")
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(
        // every form input submitted in newEmployer object
        @ModelAttribute @Valid Employer newEmployer,
        Errors errors,
        Model model // a test fails if line deleted or if not last parameter
    ) {
        if (errors.hasErrors()) { return "employers/add"; }

        employerRepository.save(newEmployer);

        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(
        Model model, // enables setting html variables
        @PathVariable int employerId
    ) {
//      employerRepository returns Optional<Employer>
//      Optional is a container object containing either null or non-null value
//          if value is non-null,
//              .isPresent() returns true
//                  & .get() returns Object type value
//      CrudRepository method --> findById()
//      custom method call example: employerRepository.findByLocation("USA");

        Optional<Employer> optEmployer = employerRepository.findById(employerId);

        if (optEmployer.isPresent()) {
            model.addAttribute("employer", optEmployer.get());
            return "employers/view";
        }
        return "redirect:../";
    }
}
