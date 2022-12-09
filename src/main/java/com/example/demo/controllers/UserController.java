package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        logger.info("=========== Start finding user by id [{}] ===========", id);
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        logger.info("=========== Start finding user by username [{}] ===========", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Did not find user with username [{}]", username);
        } else {
            logger.info("Found user with username [{}]", username);
        }
        logger.info("=========== End finding user by username [{}] ===========", username);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest) {
        logger.info("=========== Start create user ===========");
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        if (!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            logger.debug("Confirm password does not match password");
            return ResponseEntity.badRequest().body("Confirm password does not match password");
        }
        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        logger.info("Created user with username, {}.", user.getUsername());
        logger.info("=========== End create user ===========");
        return ResponseEntity.ok(user);
    }

}
