package haagahelia.fi.ProjectManagement.model.project;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class ProjectPredicate {

	public static Predicate search(String name, ProjectStatus status) {
		
		QProject project = QProject.project;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(name != null) {
			builder.and(project.name.eq(name));
		}
		if (status != null) {
			builder.and(project.status.eq(status));
		}
		
		return builder;
		
	}
	
}
