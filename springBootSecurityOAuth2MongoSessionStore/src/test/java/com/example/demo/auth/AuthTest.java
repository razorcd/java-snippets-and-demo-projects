package com.example.demo.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class AuthTest {
    @Value("${app.oauth.client-id}") String clientId;
    @Value("${app.oauth.client-secret}") String clientSecret;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    String username = "user";
    String password = "password";
    String authHeader;

    @PostConstruct
    public void init() {
        authHeader = Base64.getEncoder()
                .encodeToString(new StringBuilder().append(clientId).append(":").append(clientSecret).toString().getBytes());
    }

    @Before
    public void before() {
        // factory to create dummy user
    }

    @Test
    public void loginWithGoodCredentials() throws Exception {
        MvcResult response = mockMvc.perform(post("/oauth/token")
                .header("Authorization", "Basic "+authHeader)
                .param("grant_type", "password")
                .param("username", username)
                .param("password", password))
                .andDo(result -> Optional.ofNullable(result.getResolvedException()).ifPresent(Exception::printStackTrace))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> responseBody = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<Map<String, String>>(){});
        String accessToken = responseBody.get("access_token");

        mockMvc.perform(get("/principal").param("access_token", accessToken)).andExpect(status().isOk());
        mockMvc.perform(get("/principal").param("access_token", "wrong token")).andExpect(status().isUnauthorized());
    }

    @Test
    public void loginWithWrongPassword() throws Exception {
        mockMvc.perform(post("/oauth/token")
                .header("Authorization", "Basic "+authHeader)
                .param("grant_type", "password")
                .param("username", username)
                .param("password", "wrong-password"))
//                .andDo(result -> Optional.ofNullable(result.getResolvedException()).ifPresent(Exception::printStackTrace))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithWrongUsername() throws Exception {
        mockMvc.perform(post("/oauth/token")
                .header("Authorization", "Basic "+authHeader)
                .param("grant_type", "password")
                .param("username", "wrong-username")
                .param("password", password))
//                .andDo(result -> Optional.ofNullable(result.getResolvedException()).ifPresent(Exception::printStackTrace))
                .andExpect(status().isBadRequest());
    }

    //TODO: test access token expiration and reset with refresh_token
    //TODO: implement logout
}