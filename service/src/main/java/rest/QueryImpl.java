package rest;

import org.springframework.stereotype.Component;

@Component
public class QueryImpl implements Query {

    public String query() {
        return "template";
    }
}
