package com.ezen.springmvc.domain.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UploadFile {
	private String uploadFileName;
	private String storeFileName;
}
