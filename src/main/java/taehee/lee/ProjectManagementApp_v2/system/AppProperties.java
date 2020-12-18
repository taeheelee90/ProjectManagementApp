package taehee.lee.ProjectManagementApp_v2.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("projectmanagement")
public class AppProperties {

	private String host;
}
