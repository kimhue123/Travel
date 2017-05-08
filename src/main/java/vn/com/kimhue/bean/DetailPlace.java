package vn.com.kimhue.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DetailPlace {
	private CommonsMultipartFile[] fileDatas;
	private String detail;
	public CommonsMultipartFile[] getFileDatas() {
		return fileDatas;
	}
	public void setFileDatas(CommonsMultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

}
