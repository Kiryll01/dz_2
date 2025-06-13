package com.example.jsonplaceholderclone.controller;

import com.example.jsonplaceholderclone.config.TestSecurityConfig;
import com.example.jsonplaceholderclone.model.*;
import com.example.jsonplaceholderclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User testUser;
    private Address testAddress;
    private Geo testGeo;
    private Company testCompany;

    @BeforeEach
    void setUp() {
        testGeo = new Geo();
        testGeo.setLat("-37.3159");
        testGeo.setLng("81.1496");

        testAddress = new Address();
        testAddress.setStreet("Kulas Light");
        testAddress.setSuite("Apt. 556");
        testAddress.setCity("Gwenborough");
        testAddress.setZipcode("92998-3874");
        testAddress.setGeo(testGeo);

        testCompany = new Company();
        testCompany.setName("Romaguera-Crona");
        testCompany.setCatchPhrase("Multi-layered client-server neural-net");
        testCompany.setBs("harness real-time e-markets");

        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Leanne Graham");
        testUser.setUsername("Bret");
        testUser.setEmail("Sincere@april.biz");
        testUser.setAddress(testAddress);
        testUser.setPhone("1-770-736-8031 x56442");
        testUser.setWebsite("hildegard.org");
        testUser.setCompany(testCompany);
    }

    @Test
    void getAllUsers_ShouldReturnUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(testUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Leanne Graham"))
                .andExpect(jsonPath("$[0].username").value("Bret"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Leanne Graham"))
                .andExpect(jsonPath("$.username").value("Bret"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        when(userService.getUserById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(999L);
    }

    @Test
    void createUser_WithValidData_ShouldReturnCreatedUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Leanne Graham"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void updateUser_WhenUserExists_ShouldReturnUpdatedUser() throws Exception {
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(Optional.of(testUser));

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Leanne Graham"));

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void updateUser_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        when(userService.updateUser(eq(999L), any(User.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/users/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).updateUser(eq(999L), any(User.class));
    }

    @Test
    void deleteUser_WhenUserExists_ShouldReturn200() throws Exception {
        when(userService.deleteUser(1L)).thenReturn(true);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        when(userService.deleteUser(999L)).thenReturn(false);

        mockMvc.perform(delete("/users/999"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).deleteUser(999L);
    }
} 