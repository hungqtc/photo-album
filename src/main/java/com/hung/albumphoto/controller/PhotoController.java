package com.hung.albumphoto.controller;

import com.hung.albumphoto.entity.AlbumEntity;
import com.hung.albumphoto.service.PhotoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")

public class PhotoController {
    @Autowired
    PhotoService photoService;

    @GetMapping("/albums/{album_id}/photos")
    public ResponseEntity<?> findALlByAlbum(@PathVariable long album_id) {

        return ResponseEntity.ok(photoService.findALlByAlbum(album_id));
    }

    @GetMapping("/photos")
    public ResponseEntity<?> findALl() {
        return ResponseEntity.ok(photoService.findALl());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/albums/{album_id}/photos")
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String name,
                                    @PathVariable long album_id) throws IOException {
        return ResponseEntity.ok(photoService.create(file,name,album_id));
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/photos/{id}")
    public ResponseEntity<?> update(@RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String name,
                                    @PathVariable long id) throws IOException {

        return ResponseEntity.ok(photoService.update(file,name,id));
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping ("/photos/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        photoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
