package com.hung.albumphoto.controller;

import com.hung.albumphoto.entity.AlbumEntity;
import com.hung.albumphoto.service.AlbumService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<?> findALl() {
        return ResponseEntity.ok(albumService.findALl());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AlbumEntity album) {
        return ResponseEntity.ok(albumService.create(album));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AlbumEntity album, @PathVariable long id) {
        album.setId(id);
        return ResponseEntity.ok(albumService.update(album));
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        albumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
