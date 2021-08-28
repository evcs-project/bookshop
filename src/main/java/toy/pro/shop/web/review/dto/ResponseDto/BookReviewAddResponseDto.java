package toy.pro.shop.web.review.dto.ResponseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReviewAddResponseDto {
    private List<ReviewDto> reviewDtoList;
}
