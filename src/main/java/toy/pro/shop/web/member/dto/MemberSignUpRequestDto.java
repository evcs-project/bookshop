package toy.pro.shop.web.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import toy.pro.shop.web.member.domain.RoleName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class MemberSignUpRequestDto {

    @ApiModelProperty(value = "이메일", example = "naver@naver.com", required = true)
    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @ApiModelProperty(value = "패스워드", required = true, example = "password")
    @NotNull(message = "패스워드를 입력해주세요.")
    private String password;

    @ApiModelProperty(value = "이름", required = true, example = "abcd")
    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @ApiModelProperty(value = "Role", required = true, example = "[\"ADMIN\", \"USER\"]", notes = "권한은 최소 1개이상입니다.")
    @NotNull(message = "Role 을 입력해주세요.")
    @Size(min = 1, message = "유저는 최소 1개의 권한이 있어야 됩니다.(일반유저 : USER, 어드민유저: ADMIN)", max = 2)
    private List<RoleName> roleName;

}
