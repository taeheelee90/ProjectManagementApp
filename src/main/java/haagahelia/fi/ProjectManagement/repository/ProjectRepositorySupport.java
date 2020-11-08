package haagahelia.fi.ProjectManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.querydsl.jpa.impl.JPAQueryFactory;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.model.project.QProject;

@RepositoryRestResource
public class ProjectRepositorySupport extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;
	private QProject project = QProject.project;
	
	public ProjectRepositorySupport(JPAQueryFactory queryFactory) {
		super(Project.class);
		this.queryFactory = queryFactory;
	}
	
	public List<Project> findByNameAndStatus (String name, ProjectStatus status){
		return queryFactory.selectFrom(project)
				.where(project.name.like("%" + name + "%")
						.and(project.status.eq(status)))
				.fetch();
	}
	
}
