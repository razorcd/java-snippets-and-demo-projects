package com.example.demo.controller;

import com.example.demo.persistanceNoSql.ToolDocument;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyToolDocumentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getToolByIdTest() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tools/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json;charset=UTF8"))
//                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Resource<ToolDocument> resource = objectMapper.readValue(response, new TypeReference<Resource<ToolDocument>>(){});

        Assert.assertTrue("should have 'self' link", resource.hasLink("self"));
        Assert.assertEquals(resource.getLink("self"), resource.getId());

        Assert.assertTrue("should have 'tools' link", resource.hasLink("tools"));

        Assert.assertTrue("should have 'tools2' link", resource.hasLink("tools2"));
        Assert.assertEquals(resource.getLink("tools2"), linkTo(methodOn(MyToolDocumentController.class).getTools()).withRel("tools2"));  // uri to another method
    }



//    @Test
//    public void getToolsTest() throws Exception {
//        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tools"))
//            .andExpect(content().contentTypeCompatibleWith("application/hal+json;charset=UTF8"))
////            .andDo(print())    // prints the request. But it is printing it by default anyway.
//            .andExpect(status().is2xxSuccessful())
//            .andReturn().getResponse().getContentAsString();
//
//        Resources<ToolDocument> resources = objectMapper.readValue(response, new TypeReference<Resources<String>>(){});
//
//        Assert.assertTrue("should have self link", resources.hasLink("self"));
//        Assert.assertEquals(resources.getLink("self"), resources.getId());
//
//        System.out.println(resources.toString());
//    }
}
