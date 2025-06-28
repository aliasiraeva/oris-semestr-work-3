package com.example.announcement_service.entity;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dictionary.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    @Enumerated(STRING)
    @Column(name = "announcement_category", nullable = false)
    private AnnouncementCategory announcementCategory;
    @Enumerated(STRING)
    private SubscriptionStatus status;
    @ManyToMany
    @JoinTable(name = "subscription_tag", joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
}
