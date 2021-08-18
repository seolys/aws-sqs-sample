package seol.sample.sqsconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import seol.sample.sqsconsumer.dto.UserDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsStandardConsumer {

	private final ObjectMapper objectMapper;

	@SqsListener(value = "${cloud.aws.sqs.standard.string.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void stringListener(@Headers Map<String, String> headers, @Payload String message, Acknowledgment ack) {
		log.info("[standard][string] message={}", message);
		log.info("[standard][string] headers={}", headers);
		log.info("[standard][string] ack={}", ack != null);
		ack.acknowledge();
	}

	@SneakyThrows
	@SqsListener(value = "${cloud.aws.sqs.standard.object.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	private void objectListener(@Headers Map<String, String> headers, @Payload UserDto userDto, Acknowledgment ack) {
		log.info("[standard][object] userDto={}", userDto);
		log.info("[standard][object] headers={}", headers);
		log.info("[standard][object] ack={}", ack != null);
		ack.acknowledge();
	}

}
