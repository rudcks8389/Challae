package com.ezen.springmvc.web.upload.service;

import com.ezen.springmvc.web.upload.dto.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    // 단일 업로드 파일 저장
    public UploadFile storeFile(MultipartFile multipartFile);

    // 다중 업로드 파일 들 저장
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles);

    // 업로드 파일 목록 반환
    public List<String> getStoreFiles();
}
