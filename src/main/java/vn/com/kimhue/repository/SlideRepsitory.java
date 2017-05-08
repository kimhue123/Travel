package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.kimhue.model.SlideModel;

@Repository
public interface SlideRepsitory extends JpaRepository<SlideModel, Integer>{

}
