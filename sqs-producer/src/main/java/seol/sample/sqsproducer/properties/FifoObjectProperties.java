package seol.sample.sqsproducer.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cloud.aws.sqs.fifo.object")
@Getter @Setter
public class FifoObjectProperties {

	private String name;

	private String url;

}
