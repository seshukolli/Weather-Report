package com.assignment.weather.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */


@Entity
@Table(name = "wthr_rprt")
@Data
public class WeatherReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wr_id")
    private Long id;

    @ManyToOne
    @JoinColumn( name = "wr_gc", referencedColumnName = "gc_id" )
    private GeoCodeModel geoCodeModel;

    @Column(name = "wr_crtd_on")
    private Calendar createdOn;

    @Column(name = "wr_mn_dscrptn")
    private String mainDescription;

    @Column(name = "wr_tmprtr")
    private String temperature;

    @Column(name = "wr_tmprtr_min")
    private String temperatureMin;

    @Column(name = "wr_tmprtr_max")
    private String temperatureMax;

    @Column(name = "wr_hmdty")
    private String humidity;

    @Column(name = "wr_wnd_Spd")
    private String windSpeed;

}
