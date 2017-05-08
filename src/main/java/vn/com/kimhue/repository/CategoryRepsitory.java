package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.kimhue.model.CategoryModel;

@Repository
public interface CategoryRepsitory extends JpaRepository<CategoryModel, Integer> {

}
