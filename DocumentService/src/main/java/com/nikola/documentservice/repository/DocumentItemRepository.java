package com.nikola.documentservice.repository;

import com.nikola.documentservice.entity.DocumentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long> {
}
