package vn.com.kimhue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.kimhue.model.DetailPlaceModel;
import vn.com.kimhue.repository.DetailPlaceRepository;

@Service
@Repository
@Transactional
public class DetailPlaceDao {
	@Autowired
	private DetailPlaceRepository detailRepository;

	

	public DetailPlaceModel save(DetailPlaceModel detail) {
		return detailRepository.save(detail);
		
	}


	
	public void deleteDetailPlaceById(int id) {
		detailRepository.deleteDetailPlaceById(id);
		
	}
	

}
