package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "Address")
@NoArgsConstructor
public class Address{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "aid")
        private int id;
        @Column(name = "street")
        private String street;
        @Column(name = "city")
        private String city;
}
