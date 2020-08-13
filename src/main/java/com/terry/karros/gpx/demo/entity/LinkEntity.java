package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "Link")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_entity_sequence")
    @SequenceGenerator(name = "link_entity_sequence", allocationSize = 10)
    long id;

    private String href;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
