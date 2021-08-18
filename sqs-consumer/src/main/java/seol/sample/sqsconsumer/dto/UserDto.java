package seol.sample.sqsconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long userNo;

	private String userId;

	private String userNm;

	private Integer age;

}
