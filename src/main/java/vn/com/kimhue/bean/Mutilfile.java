package vn.com.kimhue.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Mutilfile {
	private CommonsMultipartFile[] fileDatas;

	public CommonsMultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(CommonsMultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}
	

}
