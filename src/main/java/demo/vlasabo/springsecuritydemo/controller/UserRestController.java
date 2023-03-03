package demo.vlasabo.springsecuritydemo.controller;

import demo.vlasabo.springsecuritydemo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final List<User> users;
    UserRestController() {
        users = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            users.add(new User(i, "name" + i, "lastName" + i));
        }
    }

    @GetMapping
    public List<User> getAll() {
        return users ;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst().orElse(null);
    }

    @PostMapping("/add")
    public User create(@RequestBody User user){
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        users.removeIf(user -> Objects.equals(user.getId(), id));
    }
}
