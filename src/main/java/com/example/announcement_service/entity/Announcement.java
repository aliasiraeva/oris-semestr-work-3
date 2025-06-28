package com.example.announcement_service.entity;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private AnnouncementCategory category;
    @Column(nullable = false)
    private String text;
    @Column(name = "image_url")
    private String imageUrl;
    private Double lat;
    private Double lng;
    private String address;
    @Column(name = "phone_number", nullable = false)
    @Size(min = 11, max = 12, message = "Некорректный номер телефона")
    private String phoneNumber;
    private LocalDateTime date;
    @ManyToMany
    @JoinTable(name = "announcement_tag", joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

}
