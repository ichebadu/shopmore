package com.sm.shopmore.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseOtp {
    private String message;
    private LocalDateTime localDateTime;
}
