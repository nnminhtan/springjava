package dingus.co.nnminhtan.repositories;

import dingus.co.nnminhtan.entities.Category;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long>{
    default List<Category> findAllCategories(Integer pageNo,
                                             Integer pageSize,
                                             String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }
}
