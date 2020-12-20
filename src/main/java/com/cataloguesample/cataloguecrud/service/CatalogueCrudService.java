package com.cataloguesample.cataloguecrud.service;

import com.cataloguesample.cataloguecrud.exception.ResourceNotFoundException;
import com.cataloguesample.cataloguecrud.model.CatalogueItem;
import com.cataloguesample.cataloguecrud.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CatalogueCrudService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    public CatalogueItem getCatalogueItem(String skuNumber) throws ResourceNotFoundException {
        return getCatalogueItemBySku(skuNumber);
    }

    private CatalogueItem getCatalogueItemBySku(String skuNumber) throws ResourceNotFoundException {
        CatalogueItem catalogueItem = catalogueRepository.findBySku(skuNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Catalogue Item not found for the provided SKU :: %s" , skuNumber)));

        return catalogueItem;
    }


    public Long addCatalogItem(CatalogueItem catalogueItem) {
         catalogueItem.setCreatedOn(new Date());

        return catalogueRepository.save(catalogueItem).getId();


    }


}
