package seol.sample.sqsproducer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	private Long userNo;

	private String userId;

	private String userNm;

	private Integer age;

}
