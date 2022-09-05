package example.astralifetesting.config.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private boolean status;
    private T data;
    private String message;

    public BaseResponse(boolean status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
