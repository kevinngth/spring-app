package com.training.book_store.app.repository;

import com.training.book_store.app.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StoreRepository extends CrudRepository<Store, Long> {
    List<Store> findByName(String name);
}
