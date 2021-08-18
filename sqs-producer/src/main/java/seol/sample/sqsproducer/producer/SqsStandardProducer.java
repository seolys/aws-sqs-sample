package seol.sample.sqsproducer.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import seol.sample.sqsproducer.dto.UserDto;
import seol.sample.sqsproducer.properties.StandardObjectProperties;
import seol.sample.sqsproducer.properties.StandardStringProperties;

@Slf4j
@Component
public class SqsStandardProducer {

	private final QueueMessagingTemplate queueMessagingTemplate;
	private final StandardStringProperties stringProperties;
	private final StandardObjectProperties objectProperties;

	@Autowired
	public SqsStandardProducer(
			AmazonSQS amazonSqs,
			StandardStringProperties stringProperties,
			StandardObjectProperties objectProperties
	) {
		this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSqs);
		this.stringProperties = stringProperties;
		this.objectProperties = objectProperties;
	}

	public void sendMessage(String message) {
		log.debug("queueName={}, meesage={}", stringProperties.getName(), message);

		Message<String> newMessage = MessageBuilder.withPayload(message).build();
		queueMessagingTemplate.send(stringProperties.getName(), newMessage);
	}

	public void sendUserDto(UserDto userDto) {
		queueMessagingTemplate.convertAndSend(objectProperties.getName(), userDto);
	}
}
