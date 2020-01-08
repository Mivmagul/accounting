package com.mivmagul.stakelogic.accounting.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long Id;

    public BaseEntity() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "Id=" + Id + '}';
    }
}
