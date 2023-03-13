package demo.vlasabo.springsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
}
