package com.ezen.springmvc.web.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.ezen.springmvc.web.upload.dto.UploadFile;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    // private String location = "c:/ezen-fullstack/workspace/spring_mvc_jsp/upload/";
    // application.properties에 설정한 key에 대한 value 읽기
    @Value("${upload.dir}")
    private String uploadDirectory;

    @PostConstruct
    public void makeUploadDirectory() {
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    /** 단일 업로드 파일 저장 */
    public UploadFile storeFile(MultipartFile multipartFile) {
        String uploadFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(uploadFileName);
        log.info("사용자가 업로드한 파일명 : {}", uploadFileName);
        log.info("실제 저장된 파일명 : {}", storeFileName);
        try {
            multipartFile.transferTo(new File(uploadDirectory + storeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new UploadFile(uploadFileName, storeFileName);
    }

    /** 다중 업로드 파일 저장 */
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) {
        List<UploadFile> storeFileResult = new ArrayList<UploadFile>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                // 업로드 파일 저장
                UploadFile uploadFile = null;
                uploadFile = storeFile(multipartFile);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }

    @Override
    public List<String> getStoreFiles() {
        // DB에 저장된 파일 목록 반환해야 하지만 편의상 실제 저장된 파일명 반환
        List<String> list = new ArrayList<>();
        File directory = new File(uploadDirectory);
        File[] files = directory.listFiles();
        Arrays.asList(files).forEach(file -> {
            list.add(file.getName());
        });
        return list;
    }

    // 업로드된 파일이 중복되지 않게 저장될 파일명 생성
	private String createStoreFileName(String uploadFileName) {
        int position = uploadFileName.lastIndexOf(".");
        String prefix =uploadFileName.substring(0, position);
        String suffix = uploadFileName.substring(position + 1);
		String uuid = UUID.randomUUID().toString();
		return prefix + "-" + uuid + "." + suffix;
	}
}
