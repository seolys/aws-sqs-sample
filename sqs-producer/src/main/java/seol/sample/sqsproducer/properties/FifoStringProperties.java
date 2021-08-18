package seol.sample.sqsproducer.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cloud.aws.sqs.fifo.string")
@Getter @Setter
public class FifoStringProperties {

	private String name;

	private String url;

}
