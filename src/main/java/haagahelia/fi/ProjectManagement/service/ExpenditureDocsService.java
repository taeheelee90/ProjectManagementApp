package haagahelia.fi.ProjectManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import haagahelia.fi.ProjectManagement.model.project.ExpenditureDocs;
import haagahelia.fi.ProjectManagement.repository.ExpenditureDocsRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import lombok.RequiredArgsConstructor;

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
