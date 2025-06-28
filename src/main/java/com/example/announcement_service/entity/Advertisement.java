package com.example.announcement_service.entity;

import jakarta.persistence.*;

@Entity

public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url", nullable = false)
    private String image;
    @Column(name = "short_text", nullable = false)
    private String shortText;
    @Column(name = "website_url")
    private String websiteUrl;
}
