package com.plist.repository;

import com.plist.entity.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository <E extends AbstractEntity> extends CrudRepository<E, Long> {
}
