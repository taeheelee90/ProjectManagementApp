package haagahelia.fi.ProjectManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectExpenditureService {

	private final ProjectRepository pRepository;
	private final ProjectExpenditureRepository peRepository;
	
	/*
	 * Add Expenditure
	 */
	@Transactional
	public Long addExpnditure (Long projectId, int cost, String description) {
		
		// Create ProjectExpenditure
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		pRepository.findById(projectId).ifPresent(p -> projectExpenditure.createExpenditure(p, cost, description));
		
		
		// Save ProjectExpenditure
		peRepository.save(projectExpenditure);
		
		return projectExpenditure.getId();
	}
}
