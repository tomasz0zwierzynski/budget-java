package pl.tomzwi.budget.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/${security.endpoint.api.prefix}" )
public class ResourceController {

    @GetMapping( "/resource" )
    public String resource() {
        return "resource";
    }

}
