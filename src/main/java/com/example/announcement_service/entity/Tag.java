package com.example.announcement_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tag_name", nullable = false)
    private String tagName;
    @ManyToMany(mappedBy = "tags")
    private Set<Announcement> announcements;
    @ManyToMany(mappedBy = "tags")
    private Set<Subscription> subscriptions;
}
