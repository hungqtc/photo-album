package com.hung.albumphoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "album")
public class AlbumEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
