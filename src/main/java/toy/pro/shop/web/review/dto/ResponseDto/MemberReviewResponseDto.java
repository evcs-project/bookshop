package toy.pro.shop.web.review.dto.ResponseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberReviewResponseDto {
    private List<ReviewDto> reviewDtoList;
}
