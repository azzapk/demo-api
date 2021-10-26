package com.domain.demoapi.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_category")
@Setter
@Getter
@SQLDelete(sql = "UPDATE tb_category SET deleted = true WHERE id=?")
@FilterDef(name = "deletedCategoryFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedCategoryFilter", condition = "deleted = :isDeleted")
public class Category extends BaseEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    private boolean deleted = Boolean.FALSE;

}
