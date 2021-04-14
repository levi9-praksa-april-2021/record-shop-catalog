package com.recordshop.catalog.domain.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("select r from Record r left join r.genres g left join r.artists a where " +
            "(:album is null or r.album like :album) and " +
            "(:genre is null or g.name like :genre) and " +
            "(:artist is null or concat(a.firstName, ' ', a.lastName) like :artist)")
    List<Record> searchRecords(
            @Param(value="album") String album,
            @Param(value="genre") String genre,
            @Param(value="artist") String artist);
}
