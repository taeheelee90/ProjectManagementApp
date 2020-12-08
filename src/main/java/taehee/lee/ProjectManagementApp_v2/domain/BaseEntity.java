package taehee.lee.ProjectManagementApp_v2.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/*
 * Base Entity to be extended to all Entities
 */


@Getter @Setter
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;


	
}

