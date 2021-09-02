package toy.pro.shop.web.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MemberChangePasswordDto {
    @ApiModelProperty(value = "패스워드", required = true, example = "password")
    @NotNull(message = "패스워드를 입력해주세요.")
    private String password;
}
