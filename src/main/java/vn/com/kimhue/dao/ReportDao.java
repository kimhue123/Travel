package vn.com.kimhue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kimhue.model.ReportModel;
import vn.com.kimhue.repository.reportRepsitory;
@Service
public class ReportDao {
	@Autowired
	private reportRepsitory reportRepsitory;
	public void save(ReportModel report) {
		reportRepsitory.save(report);
		
	}
	public List<ReportModel> getList() {
		return reportRepsitory.findAll();
	}
	public ReportModel getReportById(int id) {
		return reportRepsitory.findOne(id);
	}
	public void delete(int id) {
		reportRepsitory.delete(id);
		
	}

}
