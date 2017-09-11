package com.example.demo.controller;

import com.example.demo.persistanceNoSql.ToolDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/tools", produces = MediaTypes.HAL_JSON_VALUE)
public class MyToolDocumentController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Resources<Resource<ToolDocument>> getTools() {
        Collection<ToolDocument> tools = new ArrayList<>();
        tools.add(new ToolDocument("tool1", "T1", 3.1d));

        List<Resource<ToolDocument>> toolResources = tools.stream().map((ToolDocument tool) ->
            new Resource<ToolDocument>(tool,
                    BasicLinkBuilder.linkToCurrentMapping().slash("tools").slash(tool.getToolId()).withSelfRel())
        ).collect(Collectors.toList());

        return new Resources<Resource<ToolDocument>>(toolResources,
                BasicLinkBuilder.linkToCurrentMapping().withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resource<ToolDocument> getToolById(@PathVariable String id, @PathVariable Optional<Boolean> optionalVar) {
        if (optionalVar.isPresent()) { System.out.println(" ! Found optional variable: "+optionalVar); }

        ToolDocument t = new ToolDocument();
        return new Resource<>(t,
                BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
                BasicLinkBuilder.linkToCurrentMapping().slash("tools").withRel("tools"),
                linkTo(methodOn(MyToolDocumentController.class).getTools()).withRel("tools2")); // uri to another method
    }
}
