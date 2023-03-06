package demo.vlasabo.springsecuritydemo.controller;

import demo.vlasabo.springsecuritydemo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
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
        for (long i = 0; i < 3; i++) {
            users.add(new User(i, "name" + i, "lastName" + i));
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> getAll() {
        return users ;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public User getById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst().orElse(null);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('users:write')")
    public User create(@RequestBody User user){
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable Long id){
        users.removeIf(user -> Objects.equals(user.getId(), id));
    }
}
