package com.ezen.springmvc.domain.common.service;

import com.ezen.springmvc.domain.common.dto.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    // 단일 업로드 파일 저장
    public UploadFile storeFile(MultipartFile multipartFile, String storePath);

    // 다중 업로드 파일 들 저장
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles, String storePath);

    // 업로드 파일 목록 반환
    public List<String> getStoreFiles(String storePath);
}
