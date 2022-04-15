package com.abnamro.assessment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long personId;

    @Column(name="person_name")
    private String personName;

    @Column(name="birthDate")
    private LocalDate birthDate;
}
