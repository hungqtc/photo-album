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
        AlbumEntity _album = new AlbumEntity();
        _album.setName(album.getName());
        _album.setDescription(album.getDescription());
        return albumRepository.save(_album);
    }

    @Override
    public AlbumEntity update(AlbumEntity album) {
        Optional<AlbumEntity> albumData = albumRepository.findById(album.getId());
            AlbumEntity _album = albumData.get();
            _album.setDescription(album.getName());
            _album.setName(album.getName());
            return albumRepository.save(_album);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
