package com.assignment.weather.respository;

import com.assignment.weather.model.GeoCodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Repository
public interface GeoCodeModelRepository extends JpaRepository<GeoCodeModel, Integer> {

    Optional<GeoCodeModel> findByZipCode(String zipCode );

}
