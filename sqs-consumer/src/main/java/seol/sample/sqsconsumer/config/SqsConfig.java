package seol.sample.sqsconsumer.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SqsConfig {

	@Bean
	SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
		SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
		factory.setAmazonSqs(amazonSQSAsync);
		factory.setMaxNumberOfMessages(10);
		factory.setWaitTimeOut(20);// Long polling 설정 0~20
		return factory;
	}
}
