package com.example.artGallery.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "showing_participant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ShowingParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    Customer customer;
}
