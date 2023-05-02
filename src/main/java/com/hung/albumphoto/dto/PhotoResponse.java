package com.hung.albumphoto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoResponse extends BaseDTO{
    private String name;

    private String picture_url;

    private Long album_id;
}
