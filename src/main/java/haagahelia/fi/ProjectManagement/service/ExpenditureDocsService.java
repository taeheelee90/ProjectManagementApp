package haagahelia.fi.ProjectManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import haagahelia.fi.ProjectManagement.model.project.ExpenditureDocs;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ExpenditureDocsRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpenditureDocsService {

	private final ExpenditureDocsRepository repository;
	private final ProjectExpenditureRepository peRepository;

	// Save files
	public ExpenditureDocs SaveFile(MultipartFile file, Long expenditureId) {
		String docname = file.getOriginalFilename();
					
		try {
			ExpenditureDocs doc = new ExpenditureDocs();
			//(file.getBytes(), file.getContentType(), docname, expenditure)
			doc.setFile(file.getBytes());
			doc.setFileType(file.getContentType());
			doc.setFileName(docname);
			peRepository.findById(expenditureId).ifPresent(expenditure -> doc.setProjectExpenditure(expenditure));
			
			
			return repository.save(doc);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// Find one file
	public Optional<ExpenditureDocs> getFile(Long fileId) {
		return repository.findById(fileId);
	}

	// Find all files
	public List<ExpenditureDocs> getFiles() {
		return (List<ExpenditureDocs>) repository.findAll();
	}

}
