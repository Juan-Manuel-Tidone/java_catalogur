package com.cataloguesample.cataloguecrud.service;

import com.cataloguesample.cataloguecrud.exception.ResourceNotFoundException;
import com.cataloguesample.cataloguecrud.model.CatalogueItem;
import com.cataloguesample.cataloguecrud.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CatalogueCrudService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    public List<CatalogueItem> getCatalogueItems(){

        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        return catalogueRepository.findAll(sort);
    }

    public CatalogueItem getCatalogueItem(String skuNumber) throws  ResourceNotFoundException{

        return getCatalogueItemBySku(skuNumber);
    }

    public List<CatalogueItem> getCatalogueItemsByName(String name){

        return catalogueRepository.findByNameIgnoreCase(name);
    }

   public Long addCatalogueItem(CatalogueItem catalogueItem){

        catalogueItem.setCreatedOn(new Date());

        return catalogueRepository.save(catalogueItem).getId();
   }


    public Long addCatalogItem(CatalogueItem catalogueItem) {
         catalogueItem.setCreatedOn(new Date());

        return catalogueRepository.save(catalogueItem).getId();


    }

    public void updateCatalogueItem(CatalogueItem catalogueItem) throws  ResourceNotFoundException{

        CatalogueItem catalogueItemFromDB = getCatalogueItemBySku(catalogueItem.getSku());

        catalogueItemFromDB.setName(catalogueItem.getName());
        catalogueItemFromDB.setDescription(catalogueItem.getDescription());
        catalogueItemFromDB.setPrice(catalogueItem.getPrice());
        catalogueItemFromDB.setInventory(catalogueItem.getInventory());
        catalogueItemFromDB.setUpdatedOn(new Date());

        catalogueRepository.save(catalogueItemFromDB);
    }

    public void deleteCatalogueItem(CatalogueItem catalogueItem)
    {
        catalogueRepository.delete(catalogueItem);
    }

    public void deleteBySku(String skuNumber) throws ResourceNotFoundException
    {
        CatalogueItem catalogueItem = getCatalogueItemBySku(skuNumber);

        catalogueRepository.delete(catalogueItem);
    }

    //region Private Methods

    private CatalogueItem getCatalogueItemBySku(String skuNumber) throws ResourceNotFoundException {
        CatalogueItem catalogueItem = catalogueRepository.findBySku(skuNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Catalogue Item not found for the provided SKU :: %s" , skuNumber)));

        return catalogueItem;
    }



    //endregion


}
