package com.sm.shopmore.dto.response.auth;

import com.sm.shopmore.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;
    private String time;
    private T payload;


    public ApiResponse(T payload) {
        this.message = "api recieved";
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.payload = payload;
    }
}
