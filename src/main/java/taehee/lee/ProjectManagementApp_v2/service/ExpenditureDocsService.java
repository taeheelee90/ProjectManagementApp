package taehee.lee.ProjectManagementApp_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.project.ExpenditureDocs;
import taehee.lee.ProjectManagementApp_v2.repository.ExpenditureDocsRepository;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectExpenditureRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpenditureDocsService {

	private final ExpenditureDocsRepository repository;
	private final ProjectExpenditureRepository peRepository;

	/*
	 * Save files
	 */
	public ExpenditureDocs SaveFile(MultipartFile file, Long expenditureId) {
		
		// get fileName
		String docname = file.getOriginalFilename();

		try {
			
			// create new file
			
			ExpenditureDocs doc = new ExpenditureDocs();
			doc.setFile(file.getBytes());
			doc.setFileType(file.getContentType());
			doc.setFileName(docname);
			peRepository.findById(expenditureId).ifPresent(expenditure -> doc.setProjectExpenditure(expenditure));

			// save file
			return repository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Find One File
	 */
	public Optional<ExpenditureDocs> getFile(Long fileId) {
		return repository.findById(fileId);
	}

	/*
	 * Find All Files
	 */
	public List<ExpenditureDocs> getFiles() {
		return (List<ExpenditureDocs>) repository.findAll();
	}

}

