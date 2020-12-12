package taehee.lee.ProjectManagementApp_v2.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;
import taehee.lee.ProjectManagementApp_v2.service.AwsS3Service;



/*
 * File upload / download handled by AWS S3 storage 
 * Reference: https://devkonline.com/tutorials/content/spring-aws-s3
 * 
 */

@Controller
public class AwsS3Controller {

	@Autowired
	private AwsS3Service awsS3Service;

	private static final String bucket_name = "projectdb2020";

	// File Lists
	@GetMapping(value = "/file")
	public String fileForm(@CurrentUser AppUser appUser, Model model) {
		List<String> allFileNameInBucket = awsS3Service.getAllFileNameInBucket(bucket_name);
		model.addAttribute("s3FileNames", allFileNameInBucket);
		model.addAttribute(appUser);
		return "expenditure/files";
	}

	// File upload
	@PostMapping(value = "/file")
	public String uploadToS3(@RequestParam("files") MultipartFile multipartFile) throws IOException {
		File file = File.createTempFile("tmp", "tmp");
		multipartFile.transferTo(file);
		awsS3Service.upload(file, multipartFile.getOriginalFilename(), bucket_name);
		
		return "redirect:/file";
	}

	// Download file
	@GetMapping("/download/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String fileName) {
		ByteArrayOutputStream byteArrayOutputStream = awsS3Service.downloadFile(fileName, bucket_name);
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(byteArrayOutputStream.toByteArray());
	}

	// Delete file: Only available for ADMIN
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{filename}")
	public String deleteFile(@PathVariable("filename") String fileName) {
		awsS3Service.deleteFromBucket(bucket_name, fileName);
		return "redirect:/file";
	}

}

