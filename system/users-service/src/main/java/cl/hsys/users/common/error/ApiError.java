package cl.hsys.users.common.error;

import java.time.OffsetDateTime;

public record ApiError (
    int status,
    String message,
    OffsetDateTime timestamp
){
    public ApiError(int status, String message) {
        this(status, message, OffsetDateTime.now());

    }
}
