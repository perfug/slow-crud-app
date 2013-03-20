package com.jclarity.had_one_dismissal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    @NotNull
    @Size(min = 2)
    private String name;

    @ManyToOne
    private Location location;

    @ManyToMany(cascade = CascadeType.ALL)
    private final Set<Tag> tags = new HashSet<Tag>();

}
