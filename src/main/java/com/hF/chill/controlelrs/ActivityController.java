package com.hF.chill.controlelrs;

import com.hF.chill.models.Activity;
import com.hF.chill.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chill")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{id}")
    public String getCertain(@PathVariable("id") Activity activity, Model model) {
        model.addAttribute("activity", activity);
        return "certain";
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("activities", activityService.getAll());
        return "main";
    }

    @PostMapping("/main")
    public String create(Model model, @RequestParam String title) {
        Activity activity = new Activity(title);
        activityService.save(activity);
        model.addAttribute("activities", activityService.getAll());
        return "redirect:/chill/main";
    }

    @GetMapping("/random")
    public String random(Model model) {
        model.addAttribute("activity", activityService.randomize());
        return "random";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("activity") Activity activity, @PathVariable("id") Long id) {
        activityService.delete(id);
        return "redirect:/chill/main";
    }


}
