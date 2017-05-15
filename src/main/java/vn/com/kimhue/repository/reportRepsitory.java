package vn.com.kimhue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.kimhue.model.ReportModel;

public interface reportRepsitory extends JpaRepository<ReportModel,Integer> {

}
