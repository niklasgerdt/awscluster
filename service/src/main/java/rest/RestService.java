package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService {
    @Autowired
    private Query query = new QueryImpl();

    @RequestMapping("/{name}")
    public ValueObject get(@RequestParam(value = "name", defaultValue = "dude") String name) {
        System.out.println(query.query());
        ValueObject vo = new ValueObject(name);
        return vo;
    }
}
