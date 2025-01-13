package com.app.CANCHASBA_API.models.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "canchas")
@Entity
public class Cancha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancha_seq_gen")
    @SequenceGenerator(name = "cancha_seq_gen", sequenceName = "cancha_sequence", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="name", columnDefinition = "TEXT")
    private String name;

    @Column(name="address", columnDefinition = "TEXT")
    private String address;

    @Column(name="city", columnDefinition = "TEXT")
    private String city;

    @Column(name="zone", columnDefinition = "TEXT")
    private String zone;

    @Column(name="phone", columnDefinition = "TEXT")
    private String phone;

    @Column(name="quantity", columnDefinition = "TEXT")
    private String quantity;

    @Column(name="type", columnDefinition = "TEXT")
    private String type;

    @Column(name="size", columnDefinition = "TEXT")
    private String size;

    @Column(name="Rating", columnDefinition = "TEXT")
    private String rating;

    @Column(name="Collaborator")
    private boolean collaborator;
}
