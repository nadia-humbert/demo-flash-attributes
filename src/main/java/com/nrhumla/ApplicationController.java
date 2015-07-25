package com.nrhumla;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ApplicationController {

    private static final String NAME_FLASH_ATTRIBUTE = "name";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("default");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("name", flashMap.get(NAME_FLASH_ATTRIBUTE));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView post(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(NAME_FLASH_ATTRIBUTE, name);
        return new ModelAndView("redirect:/");
    }
}
