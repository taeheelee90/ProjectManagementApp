package taehee.lee.ProjectManagementApp_v2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectExpenditure;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectExpenditureRepository;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectRepository;

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
	public Long addExpenditure (Long projectId, int cost, String description) {
		
		// Create ProjectExpenditure
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		pRepository.findById(projectId).ifPresent(p -> projectExpenditure.setProject(p));
		projectExpenditure.setCost(cost);
		projectExpenditure.setDescription(description);
		
		pRepository.findById(projectId).ifPresent(p -> projectExpenditure.createExpenditure(p, cost, description));
	
		
		// Save ProjectExpenditure
		peRepository.save(projectExpenditure);
		
		return projectExpenditure.getId();
	
	}
	
}
