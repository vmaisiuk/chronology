package com.andersen.chronology.trips.repository.specification;

import com.andersen.chronology.trips.domain.TripEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static com.andersen.chronology.trips.util.Constants.*;

public class TripSpecification {

    public static Specification<TripEntity> getAllTrips(LocalDate startDate, LocalDate endDate, String country, String companionName, String userName) {
        Specification<TripEntity> specification = Specification
                .where((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root
                                .get(USERNAME_FIELD), userName
                ));

        specification = addCriteriaForStartDate(specification, startDate);
        specification = addCriteriaForEndDate(specification, endDate);
        specification = addCriteriaForEndCountry(specification, country);
        specification = addCriteriaForEndCompanionName(specification, companionName);

        return specification;
    }

    public static Specification<TripEntity> getAllTrips(String userName) {
        return getAllTrips(null, null, null, null, userName);
    }

    private static Specification<TripEntity> addCriteriaForStartDate(
            Specification<TripEntity> specification, LocalDate startDate) {
        if (startDate != null) {
            return specification.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                    root
                            .get(DATE_FIELD), startDate
            ));
        }
        return specification;
    }

    private static Specification<TripEntity> addCriteriaForEndDate(
            Specification<TripEntity> specification, LocalDate endDate) {
        if (endDate != null) {
            return specification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                    root
                            .get(DATE_FIELD), endDate
            ));
        }
        return specification;
    }

    private static Specification<TripEntity> addCriteriaForEndCountry(
            Specification<TripEntity> specification, String country) {
        if (country != null) {
            return specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root
                            .get(COUNTRY_FIELD), country
            ));
        }
        return specification;
    }

    private static Specification<TripEntity> addCriteriaForEndCompanionName(
            Specification<TripEntity> specification, String companionName) {
        if (companionName != null) {
            return specification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(
                    root
                            .get(TRIP_COMPANY_FIELD), companionName
            ));
        }
        return specification;
    }

}
