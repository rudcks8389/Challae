package com.ezen.springmvc.web.upload.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UploadForm {
	private String uploader;
	private String description;
	private List<MultipartFile> uploadfiles;
}
