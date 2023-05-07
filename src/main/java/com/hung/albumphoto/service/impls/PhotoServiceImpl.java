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
            response.setPictureUrl(entity.getPicture());
            response.setAlbumId(entity.getAlbumEntity().getId());
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
            response.setPictureUrl(entity.getPicture());
            response.setAlbumId(entity.getAlbumEntity().getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public PhotoResponse create(MultipartFile file, String name, long albumId) throws IOException {
        PhotoEntity entity = new PhotoEntity();
        entity.setName(name);
        entity.setPicture(fileUtil.getUrl(file));
        Optional<AlbumEntity> album = albumRepository.findById(albumId);
        entity.setAlbumEntity(album.get());
        PhotoEntity photoSaved = photoRepository.save(entity);

        PhotoResponse response = new PhotoResponse();
        response.setId(photoSaved.getId());
        response.setName(entity.getName());
        response.setAlbumId(albumId);
        response.setPictureUrl(entity.getPicture());
        return response;
    }

    @Override
    public PhotoResponse update(MultipartFile file, String name, long id) throws IOException {
        Optional<PhotoEntity> entity = photoRepository.findById(id);
        PhotoResponse response = new PhotoResponse();
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(entity.get().getId());
        photoEntity.setPicture(fileUtil.getUrl(file));
        photoEntity.setName(name);
        photoEntity.setAlbumEntity(entity.get().getAlbumEntity());
        photoRepository.save(photoEntity);

        response.setId(entity.get().getId());
        response.setName(name);
        response.setPictureUrl(fileUtil.getUrl(file));
        response.setAlbumId(entity.get().getAlbumEntity().getId());
        return response;
    }

    @Override
    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }
}
