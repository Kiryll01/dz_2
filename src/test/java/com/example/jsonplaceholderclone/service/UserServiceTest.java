package com.example.jsonplaceholderclone.service;

import com.example.jsonplaceholderclone.exception.ResourceNotFoundException;
import com.example.jsonplaceholderclone.model.*;
import com.example.jsonplaceholderclone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
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
    void getAllUsers_ShouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(testUser, users.get(0));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldReturnEmpty() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(999L);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(999L);
    }

    @Test
    void createUser_ShouldReturnSavedUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser = userService.createUser(testUser);

        assertNotNull(savedUser);
        assertEquals(testUser, savedUser);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void updateUser_WhenUserExists_ShouldReturnUpdatedUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        Optional<User> result = userService.updateUser(1L, testUser);

        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void updateUser_WhenUserDoesNotExist_ShouldReturnEmpty() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> result = userService.updateUser(999L, testUser);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(999L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_WhenUserExists_ShouldReturnTrue() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        doNothing().when(userRepository).delete(testUser);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldReturnFalse() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = userService.deleteUser(999L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(999L);
        verify(userRepository, never()).delete(any(User.class));
    }
} 