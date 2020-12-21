package com.cataloguesample.cataloguecrud.repository;

import com.cataloguesample.cataloguecrud.model.CatalogueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CatalogueRepository extends JpaRepository<CatalogueItem, Long> {

    Optional<CatalogueItem> findBySku(String sku);
    List<CatalogueItem> findByNameIgnoreCase(String name);

}
