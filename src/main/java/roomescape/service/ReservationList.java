package roomescape.service;

import org.springframework.stereotype.Component;
import roomescape.dto.ReservationResponse;
import roomescape.dto.ReservationRequest;
import roomescape.exception.InvalidReservationRequestException;
import roomescape.exception.NotFoundReservationException;
import roomescape.domain.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class ReservationList {

    private final Map<Integer, Reservation> idToReservation = new HashMap<>();
    private int index = 0;

    public List<ReservationResponse> findAll() {
        List<ReservationResponse> responses = new ArrayList<>();
        for (Reservation reservation : idToReservation.values()) {
            responses.add(toResponse(reservation));
        }
        return responses;
    }

    public ReservationResponse create(ReservationRequest request) {
        if (request.name() == null || request.name().isBlank()
                || request.date() == null || request.date().isBlank()
                || request.time() == null || request.time().isBlank()) {
            throw new InvalidReservationRequestException("이름,날짜,시간이 모두 입력되어야 합니다.");
        }
        int newId = ++index;
        Reservation reservation = new Reservation(newId, request.name(), request.date(), request.time());
        idToReservation.put(newId, reservation);
        return toResponse(reservation);
    }

    public void delete(int id) {
        if (!idToReservation.containsKey(id)) {
            throw new NotFoundReservationException("해당 분실물을 찾을 수 없습니다: " + id);
        }
        idToReservation.remove(id);
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getName(), reservation.getDate(), reservation.getTime());
    }
}


