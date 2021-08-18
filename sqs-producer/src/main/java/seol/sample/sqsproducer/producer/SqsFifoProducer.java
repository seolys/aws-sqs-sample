package seol.sample.sqsproducer.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import seol.sample.sqsproducer.dto.UserDto;
import seol.sample.sqsproducer.properties.FifoObjectProperties;
import seol.sample.sqsproducer.properties.FifoStringProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsFifoProducer {

	private final AmazonSQS amazonSQS;
	private final ObjectMapper objectMapper;
	private final FifoStringProperties stringProperties;
	private final FifoObjectProperties objectProperties;

	public SendMessageResult sendMessage(String message) {
		SendMessageRequest sendMessageRequest = new SendMessageRequest(stringProperties.getUrl(), message)
				.withMessageGroupId("messageGroup")
				.withMessageDeduplicationId(UUID.randomUUID().toString());
		return amazonSQS.sendMessage(sendMessageRequest);
	}


	@SneakyThrows
	public SendMessageResult sendUserDto(UserDto userDto) {
		SendMessageRequest sendMessageRequest = new SendMessageRequest(objectProperties.getUrl(), objectMapper.writeValueAsString(userDto))
				.withMessageGroupId("userGroup")
				.withMessageDeduplicationId(UUID.randomUUID().toString());
		return amazonSQS.sendMessage(sendMessageRequest);
	}

}
