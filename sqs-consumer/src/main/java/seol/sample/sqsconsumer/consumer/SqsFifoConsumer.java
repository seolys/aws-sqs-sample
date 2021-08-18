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
@Component // Bean 등록을 꼭 해줘야 한다!
@RequiredArgsConstructor
public class SqsFifoConsumer {

	private final ObjectMapper objectMapper;

	@SqsListener(value = "${cloud.aws.sqs.fifo.string.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void stringListener(@Headers Map<String, String> headers, @Payload String message, Acknowledgment ack) {
		log.info("[fifo][string] message={}", message);
		log.info("[fifo][string] headers={}", headers);
		log.info("[fifo][string] ack={}", ack != null);
		ack.acknowledge();
	}

	@SneakyThrows
	@SqsListener(value = "${cloud.aws.sqs.fifo.object.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//	private void objectListener(@Headers Map<String, String> headers, @Payload UserDto userDto, Acknowledgment ack) {
	private void objectListener(@Headers Map<String, String> headers, @Payload String message, Acknowledgment ack) {
		UserDto userDto = objectMapper.readValue(message, UserDto.class);
		log.info("[fifo][object] userDto={}", userDto);
		log.info("[fifo][object] headers={}", headers);
		log.info("[fifo][object] ack={}", ack != null);
		ack.acknowledge();
	}

}
