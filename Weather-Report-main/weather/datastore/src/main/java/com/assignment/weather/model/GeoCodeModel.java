package com.assignment.weather.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */


@Entity
@Table(name = "geo_code")
@Data
public class GeoCodeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gc_id")
    private Integer id;

    @Column(name = "gc_zp_cd")
    private String zipCode;

    @Column(name = "gc_lttd")
    private String latitude;

    @Column(name = "gc_lngtd")
    private String longitude;

}
