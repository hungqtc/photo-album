package com.hung.albumphoto.repository;

import com.hung.albumphoto.entity.PhotoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    @Query(value = "SELECT photo.id FROM photo ORDER BY ID DESC LIMIT 1",
            nativeQuery = true)
    int getLastedIdRecord();

    @Query(value = "SELECT * FROM photo p WHERE p.album_id = ?1",
            nativeQuery = true)
    List<PhotoEntity> findAllByAlbum(Long albumId);
}
