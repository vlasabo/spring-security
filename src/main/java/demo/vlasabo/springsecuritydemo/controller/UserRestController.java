package demo.vlasabo.springsecuritydemo.controller;

import demo.vlasabo.springsecuritydemo.model.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final List<Person> people;

    UserRestController() {
        people = new ArrayList<>();
        for (long i = 0; i < 3; i++) {
            people.add(new Person(i, "name" + i, "lastName" + i));
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<Person> getAll() {
        return people;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public Person getById(@PathVariable Long id) {
        return people.stream()
                .filter(person -> Objects.equals(person.getId(), id))
                .findFirst().orElse(null);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('users:write')")
    public Person create(@RequestBody Person person) {
        people.add(person);
        return person;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable Long id) {
        people.removeIf(person -> Objects.equals(person.getId(), id));
    }
}
