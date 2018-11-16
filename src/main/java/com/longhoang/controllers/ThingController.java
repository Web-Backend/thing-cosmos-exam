package com.longhoang.controllers;

import com.longhoang.models.Thing;
import com.longhoang.services.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ThingController {
    @Autowired
    private ThingService thingService;

    @GetMapping("/things")
    public ModelAndView showListThing() {
        Iterable<Thing> things = thingService.findAll();

        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("things", things);
        return modelAndView;
    }

    @GetMapping("/create-thing")
    public ModelAndView showCreateThingForm() {
        return new ModelAndView("create", "thing", new Thing());
    }

    @PostMapping("/create-thing")
    public ModelAndView createThing(@Valid @ModelAttribute("thing ") Thing thing,
                                    @RequestParam("name") String name,
                                    @RequestParam("magicScript") String magicScript,
                                    @RequestParam("manaNeeded") int manaNeeded,
                                    BindingResult bindingResult) {
        new Thing().validate(thing, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        }
        else {
            thingService.save(thing);

            ModelAndView modelAndView = new ModelAndView("create", "message", "Created successfully");
            modelAndView.addObject("thing", thing);
            return modelAndView;
        }
    }

    @GetMapping("/edit-thing/{id}")
    public ModelAndView showEditThingForm(@PathVariable("id") Long id) {
        return new ModelAndView("edit", "thing", thingService.findById(id));
    }

    @PostMapping("/edit-thing")
    public ModelAndView updateThing(@ModelAttribute("thing ") Thing thing,
                                    @RequestParam("name") String name,
                                    @RequestParam("magicScript") String magicScript,
                                    @RequestParam("manaNeeded") int manaNeeded) {
        thingService.save(thing);
        ModelAndView modelAndView = new ModelAndView("edit", "thing", thing);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-thing/{id}")
    public ModelAndView deleteThingForm(@PathVariable("id") Long id) {
        return new ModelAndView("delete", "thing", thingService.findById(id));
    }

    @PostMapping("/delete-thing")
    public ModelAndView deleteThing(@ModelAttribute("thing ") Thing thing) {
        thingService.remove(thing.getId());

        return new ModelAndView("delete", "message", "Deleted successfully");
    }

}
