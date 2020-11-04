package haagahelia.fi.ProjectManagement.service;

import org.springframework.stereotype.Service;

import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private ProjectRepository repository;
	
    public void updateBudget(Long projectId, int expenditure) {
    	repository.findById(projectId).ifPresent(p -> p.addExpenditure(expenditure));
    } 
}
