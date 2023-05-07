package com.hung.albumphoto.service.impls;

import com.hung.albumphoto.entity.AlbumEntity;
import com.hung.albumphoto.repository.AlbumRepository;
import com.hung.albumphoto.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<AlbumEntity> findALl() {
        return albumRepository.findAll();
    }

    @Override
    public AlbumEntity create(AlbumEntity album) {
        AlbumEntity albumNew = new AlbumEntity();
        albumNew.setName(album.getName());
        albumNew.setDescription(album.getDescription());
        return albumRepository.save(albumNew);
    }

    @Override
    public AlbumEntity update(AlbumEntity albumRequest) {
            AlbumEntity albumResponse = new AlbumEntity();
            albumResponse.setId(albumRequest.getId());
            albumResponse.setDescription(albumRequest.getDescription());
            albumResponse.setName(albumRequest.getName());
            return albumRepository.save( albumResponse);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
