package org.hothyojas.mogayobackend.config.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseResponse<T> {

    private String error;
    private T data;

    public BaseResponse(Exception e) {
        this.error = e.getClass().getSimpleName();
        this.data = null;
    }

    public BaseResponse(T data) {
        this.error = null;
        this.data = data;
    }
}
