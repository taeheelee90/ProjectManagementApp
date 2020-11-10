package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import haagahelia.fi.ProjectManagement.model.project.ExpenditureDocs;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.service.ExpenditureDocsService;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectExpenditureController {

	private final ProjectExpenditureRepository peRepository;
	private final ProjectExpenditureService service;
	private final ExpenditureDocsService docService;

	/*
	 * Expenditure Management User can submit several expenditures for one project.
	 */

	// Show All Expenditures
	@GetMapping(value = "expendituretlist/{id}")
	public String expenditureList(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditures", peRepository.findByProjectId(projectId));
		model.addAttribute("projectId", projectId);

		return "expenditure/expenditurelist";
	}

	// Add Expenditure
	@GetMapping(value = "expenditureadd/{id}")
	public String addExpenditure(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditure", new ProjectExpenditure());
		model.addAttribute("docs", new ExpenditureDocs());
		return "expenditure/addexpenditure";
	}

	// Handling expenditure submission
	@PostMapping(value = "/expenditureadd/{id}")
	public String expendtirueSubmit(@PathVariable("id") Long projectId, ProjectExpenditure expenditure) {

		service.addExpenditure(projectId, expenditure.getCost(), expenditure.getDescription());

		return "redirect:/expendituretlist/{id}";
	}

	/*
	 * File Management User can submit several documents per one expenditure.
	 */

	// File lists
	@GetMapping(value = "/file/{projectId}/{expenditureId}")
	public String fileForm(@PathVariable("projectId") Long projectId, @PathVariable("expenditureId") Long expenditureId,
			Model model) {
		model.addAttribute("docs", docService.getFiles());

		model.addAttribute("projectId", projectId);
		model.addAttribute("expenditureId", expenditureId);
		return "expenditure/files";
	}

	// Handling File Submission
	@PostMapping(value = "/file/{projectId}/{expenditureId}")
	public String uploadFiles(@PathVariable("projectId") Long projectId,
			@PathVariable("expenditureId") Long expenditureId, @RequestParam("files") MultipartFile[] files) {

		for (MultipartFile file : files) {
			docService.SaveFile(file, expenditureId);
		}
		return "redirect:/file/{projectId}/{expenditureId}";
	}

	// Download Files
	@GetMapping(value = "/downloadfile/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Long fileId) {

		ExpenditureDocs doc = docService.getFile(fileId).get();
		String fileType = doc.getFileType();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getFileName() + "\"")
				.body(new ByteArrayResource(doc.getFile()));
	}

}
