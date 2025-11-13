package roomescape.domain;

import org.springframework.stereotype.Component;
import roomescape.dto.Reservation;
import roomescape.dto.ReservationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class ReservationList {

    private final Map<Integer, Reservation> idToReservation = new HashMap<>();
    private int index = 0;

    public List<Reservation> findAll() {
        return new ArrayList<>(idToReservation.values());
    }

    public Reservation create(ReservationRequest request) {
        int newId = ++index;
        Reservation reservation  = new Reservation(newId, request.name(), request.date(), request.time());
        idToReservation.put(newId, reservation );
        return reservation;
    }

    public void delete(int id) {
        idToReservation.remove(id);
    }
}


