package com.example.jsonplaceholderclone.config;

import com.example.jsonplaceholderclone.model.*;
import com.example.jsonplaceholderclone.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {
    private final UserRepository userRepository;

    @PostConstruct
    public void seedData() {
        if (userRepository.count() == 0) {
            List<User> users = Arrays.asList(
                createUser(1L, "Leanne Graham", "Bret", "Sincere@april.biz",
                    "Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", "-37.3159", "81.1496",
                    "1-770-736-8031 x56442", "hildegard.org",
                    "Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"),
                createUser(2L, "Ervin Howell", "Antonette", "Shanna@melissa.tv",
                    "Victor Plains", "Suite 879", "Wisokyburgh", "90566-7771", "-43.9509", "-34.4618",
                    "010-692-6593 x09125", "anastasia.net",
                    "Deckow-Crist", "Proactive didactic contingency", "synergize scalable supply-chains")
                // Add more users as needed
            );
            userRepository.saveAll(users);
        }
    }

    private User createUser(Long id, String name, String username, String email,
                          String street, String suite, String city, String zipcode,
                          String lat, String lng, String phone, String website,
                          String companyName, String catchPhrase, String bs) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);

        Address address = new Address();
        address.setStreet(street);
        address.setSuite(suite);
        address.setCity(city);
        address.setZipcode(zipcode);

        Geo geo = new Geo();
        geo.setLat(lat);
        geo.setLng(lng);
        address.setGeo(geo);

        user.setAddress(address);
        user.setPhone(phone);
        user.setWebsite(website);

        Company company = new Company();
        company.setName(companyName);
        company.setCatchPhrase(catchPhrase);
        company.setBs(bs);
        user.setCompany(company);

        return user;
    }
} 