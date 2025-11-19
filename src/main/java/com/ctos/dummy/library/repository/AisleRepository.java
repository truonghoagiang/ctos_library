package com.ctos.dummy.library.repository;

import com.ctos.dummy.library.entity.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AisleRepository extends JpaRepository<Aisle, Integer> {

    List<Aisle> findAllByLibraryId(int libraryId);

    /**
     * get aisle and take out all its books
     * @param aisleName
     * @param libraryName
     * @return
     */
    @Query("SELECT a FROM Aisle a " +
            "JOIN FETCH a.aisBooks " +
            "JOIN FETCH a.library l " +
            "WHERE a.aisleName = :aisleName " +
            "AND l.libraryName = :libraryName")
    Optional<Aisle> findBookByAisleNameAndLibraryName(
            @Param("aisleName") String aisleName,
            @Param("libraryName") String libraryName
    );
}
