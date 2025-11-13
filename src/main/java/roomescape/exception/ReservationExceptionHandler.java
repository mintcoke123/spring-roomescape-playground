package roomescape.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationExceptionHandler {

    @ExceptionHandler({
            InvalidReservationRequestException.class,
            NotFoundReservationException.class
    })
    public ResponseEntity<Void> handleBadRequest(RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}
