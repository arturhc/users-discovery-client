package com.tecgurus.users.dto.integration.blog;

import java.util.*;

import lombok.Data;

@Data
public class BlogDTO {

    private Long id;
    private String title;
    private String owner;

    private List<PostDTO> posts;

}