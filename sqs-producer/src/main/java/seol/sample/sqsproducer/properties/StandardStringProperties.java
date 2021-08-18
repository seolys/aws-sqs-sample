package seol.sample.sqsproducer.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cloud.aws.sqs.standard.string")
@Getter @Setter
public class StandardStringProperties {

	private String name;

	private String url;

}
