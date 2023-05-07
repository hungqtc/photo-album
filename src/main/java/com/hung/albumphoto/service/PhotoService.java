package com.hung.albumphoto.service;

import com.hung.albumphoto.dto.PhotoResponse;
import com.hung.albumphoto.entity.PhotoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    List<PhotoResponse> findALl();

    List<PhotoResponse> findALlByAlbum(Long albumId);
    PhotoResponse create(MultipartFile file,String name, long albumId) throws IOException;

    PhotoResponse update(MultipartFile file, String name, long id) throws IOException;

    void deleteById(Long id);
}
