package toy.pro.shop.web.exception;

import aj.org.objectweb.asm.ConstantDynamic;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common
    DATA_DUPLICATION("중복된 데이터입니다.")
    , INVALID_DATA("잘못된 데이터입니다.")
    , PARAMETER_ERROR("파라미터 에러입니다.")
    , DATA_NOT_FOUND("존재하지 않는 데이터입니다.")
    , LOGIC_ERROR("로직 에러입니다.")
    , NO_PERMISSION("권한이 없습니다.")

    // member
    , USER_DUPLICATION("이미 존재하는 회원입니다.")
    , USER_NOT_FOUND("존재하지 않는 회원입니다.")
    , PASSWORD_EQUAL_ERROR("같은 비밀번호입니다.")
    , PASSWORD_NOT_MATCHED("일치하지 않는 비밀번호입니다.");

    private final String name;
}
