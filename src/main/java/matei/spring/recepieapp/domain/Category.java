package matei.spring.recepieapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data //lombok: adds getter setter, tosting, hash, equals, required constructor
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
