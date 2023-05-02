package com.hung.albumphoto.service.impls;

import com.hung.albumphoto.dto.PhotoResponse;
import com.hung.albumphoto.entity.AlbumEntity;
import com.hung.albumphoto.entity.PhotoEntity;
import com.hung.albumphoto.repository.AlbumRepository;
import com.hung.albumphoto.repository.PhotoRepository;
import com.hung.albumphoto.service.PhotoService;
import com.hung.albumphoto.ultils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    FileUtil fileUtil;

    @Override
    public List<PhotoResponse> findALl() {
        List<PhotoEntity> entities =  photoRepository.findAll();
        List<PhotoResponse> responses = new ArrayList<>();
        for (PhotoEntity entity: entities) {
            PhotoResponse response = new PhotoResponse();
            response.setId(entity.getId());
            response.setName(entity.getName());
            response.setPicture_url(fileUtil.getUrl(entity.getId()));
            response.setAlbum_id(entity.getAlbumEntity().getId());
            responses.add(response);
        }
        return responses;
    }

    public List<PhotoResponse> findALlByAlbum(Long albumId) {
        List<PhotoEntity> entities =  photoRepository.findAllByAlbum(albumId);
        List<PhotoResponse> responses = new ArrayList<>();
        for (PhotoEntity entity: entities) {
            PhotoResponse response = new PhotoResponse();
            response.setId(entity.getId());
            response.setName(entity.getName());
            response.setPicture_url(fileUtil.getUrl(entity.getId()));
            response.setAlbum_id(entity.getAlbumEntity().getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public PhotoResponse create(MultipartFile file, String name, long album_id) throws IOException {
        PhotoEntity entity = new PhotoEntity();
        entity.setName(name);
        entity.setPicture(file.getBytes());
        Optional<AlbumEntity> album = albumRepository.findById(album_id);
        entity.setAlbumEntity(album.get());
        PhotoResponse response = new PhotoResponse();
        long id = photoRepository.getLastedIdRecord();
        response.setId(id);
        response.setName(entity.getName());
        response.setAlbum_id(album_id);
        response.setPicture_url(fileUtil.getUrl(id));
        photoRepository.save(entity);
        return response;
    }

    @Override
    public PhotoResponse update(MultipartFile file, String name, long id) {
        Optional<PhotoEntity> entity = photoRepository.findById(id);
        PhotoResponse response = new PhotoResponse();
        response.setId(entity.get().getId());
        response.setName(entity.get().getName());
        response.setPicture_url(fileUtil.getUrl(id));
        response.setAlbum_id(entity.get().getAlbumEntity().getId());
        return response;
    }

    @Override
    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }
}
