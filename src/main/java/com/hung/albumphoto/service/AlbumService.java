package com.hung.albumphoto.service;

import com.hung.albumphoto.entity.AlbumEntity;

import java.util.List;

public interface AlbumService {
    List<AlbumEntity> findALl();

    AlbumEntity create(AlbumEntity album);

    AlbumEntity update(AlbumEntity album);
    void deleteById(Long id);

}
