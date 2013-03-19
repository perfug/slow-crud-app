package com.jclarity.had_one_dismissal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class JobListing {

    @NotNull
    @Size(min = 10)
    private String title;

    @NotNull
    @Size(min = 100)
    private String description;

    @NotNull
    @Size(min = 10)
    private String salary;

    private int salaryLowerBound;

    private int salaryUpperBound;

    @ManyToOne
    private Company company;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<Tag>();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date postedAt;
}
