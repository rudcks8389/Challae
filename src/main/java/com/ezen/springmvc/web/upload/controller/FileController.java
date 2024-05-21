package com.ezen.springmvc.web.upload.controller;

import com.ezen.springmvc.web.upload.dto.UploadFile;
import com.ezen.springmvc.web.upload.dto.UploadForm;
import com.ezen.springmvc.web.upload.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

	@Value("${upload.dir}")
	private String uploadDirectory;

	private final FileService fileService;

	public FileController(FileService fileService){
		this.fileService = fileService;
	}

	@GetMapping("/upload")
	public String uploadForm() {
		return "/upload/uploadForm";
	}

	@PostMapping("/upload")
	public String uploadFiles(@ModelAttribute UploadForm uploadForm, RedirectAttributes redirectAttributes)
			throws IOException {
		List<UploadFile> uploadFiles = fileService.storeFiles(uploadForm.getUploadfiles());
		log.info("저장된 파일 목록 : {}", uploadFiles);
		redirectAttributes.addFlashAttribute("uploader", uploadForm.getUploader());
		redirectAttributes.addFlashAttribute("description", uploadForm.getDescription());
		redirectAttributes.addFlashAttribute("uploadFiles", uploadFiles);
		return "redirect:/file/upload/result";
	}

	@GetMapping("/upload/result")
	public String uploadResult() {
		return "/upload/uploadResult";
	}


	@GetMapping("/download")
	public String download(Model model) {
		List<String> fileList = fileService.getStoreFiles();
		model.addAttribute("fileList", fileList);
		return "/download/downloadList";
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException {
		Path path = Paths.get(uploadDirectory + fileName);
		String contentType = Files.probeContentType(path);
		HttpHeaders headers = new HttpHeaders();
		// 응답 헤더에 파일정보 설정
		headers.setContentDisposition(
				ContentDisposition
					.builder("attachment")
					.filename(fileName, StandardCharsets.UTF_8).build());

		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	//동적 이미지의 경우 출력
	@GetMapping("/image/{fileName}")
	@ResponseBody
	public ResponseEntity<Resource> showImage(@PathVariable("fileName") String fileName) throws IOException {
		Path path = Paths.get(uploadDirectory + "/" + fileName);
		String contentType = Files.probeContentType(path);
		Resource resource = new FileSystemResource(path);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("/product")
	public String imageView(){
		return "/download/imageView";
	}
}
