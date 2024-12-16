package com.example.reveal.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String text;

    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User author;
}
