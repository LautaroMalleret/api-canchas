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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="zone")
    private String zone;
    @Column(name="phone")
    private String phone;
    @Column(name="quantity")
    private String quantity;
    @Column(name="type")
    private String type;
    @Column(name="size")
    private String size;
    @Column(name="Rating")
    private String rating;
    @Column(name="Collaborator")
    private boolean collaborator;
}
