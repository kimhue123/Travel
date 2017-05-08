package vn.com.kimhue.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFile {
	
	private CommonsMultipartFile[] fileDatas;

	public CommonsMultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(CommonsMultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}
	

}
