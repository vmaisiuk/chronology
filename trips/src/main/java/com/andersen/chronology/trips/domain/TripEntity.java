package com.andersen.chronology.trips.domain;

import com.andersen.chronology.trips.converter.StringSetConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "trip")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "trip_company")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tripCompany;
    @Column(name = "user_name")
    private String userName;
}
