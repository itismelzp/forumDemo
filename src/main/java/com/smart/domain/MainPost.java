package com.smart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("2")
public class MainPost extends Post {
}
