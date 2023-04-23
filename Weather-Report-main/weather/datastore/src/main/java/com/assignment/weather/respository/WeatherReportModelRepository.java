package com.assignment.weather.respository;


import com.assignment.weather.model.WeatherReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Repository
public interface WeatherReportModelRepository extends JpaRepository<WeatherReportModel, Long> {

    @Query(value = "select wr.* from wthr_rprt wr where date_format(wr.wr_crtd_on, '%Y-%m-%d')" +
            " and date(wr.wr_crtd_on) = curdate()", nativeQuery = true)
    Optional<WeatherReportModel> getWeatherReportForTodayByGeoCodes();

}
