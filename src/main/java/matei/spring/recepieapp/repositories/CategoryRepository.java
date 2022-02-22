package matei.spring.recepieapp.repositories;

import matei.spring.recepieapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
