package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private Query query = new QueryImpl();

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model) {
        System.out.println(query.query());
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }
}
