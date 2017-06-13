package com.example.demo.controller;

import com.example.demo.persistanceNoSql.ToolDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/tools")
public class MyToolDocumentController {

    @GetMapping("/{id}")
    public Resource<ToolDocument> getToolById(@PathVariable String id) {
        ToolDocument t = new ToolDocument();
        return new Resource<>(t,
                BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
                BasicLinkBuilder.linkToCurrentMapping().slash("tools").withRel("tools"),
                linkTo(methodOn(MyToolDocumentController.class).getTools()).withRel("tools2")); // uri to another method
    }

    @GetMapping
    public Resources<Resource<ToolDocument>> getTools() {
        List<ToolDocument> tools = new ArrayList<>();
        tools.add(new ToolDocument("tool1", "T1", 3.1d));
        return null;
//        return new Resources<>(tools,
//                BasicLinkBuilder.linkToCurrentMapping().withSelfRel());
    }
}
