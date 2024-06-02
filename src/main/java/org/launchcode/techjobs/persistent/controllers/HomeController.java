package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());
	      model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(
        @ModelAttribute @Valid Job newJob,
        Errors errors,
        Model model,
        @RequestParam int employerId,
        @RequestParam List<Integer> skills
    ) {
        if (errors.hasErrors()) {
	          model.addAttribute("title", "Add Job");
            return "add";
        }
//      searches database via employerRepository for Employer instance object
//          containing employerId to set newJob "employer" value
//      .orElse(null) merely passes IntelliJ Optional type error handling
//          for edge case where employerId object was deleted from database
//              but frontend was not updated with that deletion
//      in real world, needs error handling
//          to prevent saving newJob with "employer": null
        newJob.setEmployer(
            employerRepository.findById(employerId).orElse(null)
        );

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
//      jobRepository.findById(jobId) returns Optional<Job>
//        providing .isPresent() to verify that schema returns found Job object
//          if none found, then .orElse(null) assigns dbJob to null
//              Job dbJob = null will be valid for Job type
        Job dbJob = jobRepository.findById(jobId).orElse(null); // necessary!
        model.addAttribute("job", dbJob);
        return "view";
    }
}