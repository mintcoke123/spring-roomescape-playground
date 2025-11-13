package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.ReservationList;
import roomescape.dto.Reservation;
import roomescape.dto.ReservationRequest;

import java.net.URI;
import java.util.List;

@RestController
public class ReservationController {

    private final ReservationList reservations;

    public ReservationController(ReservationList reservations) {
        this.reservations = reservations;
    }

    @GetMapping("/reservations")
    public List<Reservation> findAll() {
        return reservations.findAll();
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> create(@RequestBody ReservationRequest request) {
        Reservation created = reservations.create(request);
        URI location = URI.create("/reservations/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        reservations.delete(id);
        return ResponseEntity.noContent().build();
    }
}
