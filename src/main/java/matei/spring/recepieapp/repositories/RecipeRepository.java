package matei.spring.recepieapp.repositories;

import matei.spring.recepieapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
