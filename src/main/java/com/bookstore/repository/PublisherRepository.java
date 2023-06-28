package com.bookstore.repository;

import com.bookstore.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    boolean existsByPublisherNameIgnoreCase(String publisherName);

    boolean existsByPublisherNameIn(List<String> publisherNames);
}
