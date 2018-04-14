package ru.ik_net.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ik_net.resume.service.IService;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
@Controller
public class SimpleController {
    /**
     * Service.
     */
    @Autowired
    private IService service;

    /**
     * @param uid uid
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public String get(@PathVariable("uid") String uid, Model model) {
        String nameService = service.getNameService();
        model.addAttribute("nameService", nameService);
        model.addAttribute("uid", uid);
        return "simple";
    }

    /** error request.
     * @return view error
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }

}
