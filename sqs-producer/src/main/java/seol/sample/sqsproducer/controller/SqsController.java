package seol.sample.sqsproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seol.sample.sqsproducer.dto.UserDto;
import seol.sample.sqsproducer.producer.SqsFifoProducer;
import seol.sample.sqsproducer.producer.SqsStandardProducer;

@RequiredArgsConstructor
@RestController
public class SqsController {

	private final SqsStandardProducer messageSender;
	private final SqsFifoProducer fifoMessageSender;

	@PostMapping("/message")
	public void sendMessage(@RequestBody String message) {
		messageSender.sendMessage(message);
	}

	@PostMapping("/object")
	public void sendMessage(@RequestBody UserDto userDto) {
		messageSender.sendUserDto(userDto);
	}

	@PostMapping("/fifo-message")
	public void fifoSendMessage(@RequestBody String message) {
		fifoMessageSender.sendMessage(message);
	}

	@PostMapping("/fifo-object")
	public void fifoSendMessage(@RequestBody UserDto userDto) {
		fifoMessageSender.sendUserDto(userDto);
	}

}
