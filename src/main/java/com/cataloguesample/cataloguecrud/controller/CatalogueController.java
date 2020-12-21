package com.cataloguesample.cataloguecrud.controller;

import com.cataloguesample.cataloguecrud.exception.ResourceNotFoundException;
import com.cataloguesample.cataloguecrud.model.CatalogueItem;
import com.cataloguesample.cataloguecrud.model.CatalogueItemList;
import com.cataloguesample.cataloguecrud.model.ResourceIdentity;
import com.cataloguesample.cataloguecrud.service.CatalogueCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CatalogueController {

    @Autowired
    private CatalogueCrudService catalogueCrudService;

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public CatalogueItemList getCatalogueItems(){
        return new CatalogueItemList(catalogueCrudService.getCatalogueItems());
    }

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS_NAME)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CatalogueItem> getCatalogueItemsByName(@PathVariable(value = "name") String name){

        return catalogueCrudService.getCatalogueItemsByName(name);
    }

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEM)
    public CatalogueItem getCatalogueItemBySKU(@PathVariable(value = "sku") String skuNumber)
        throws ResourceNotFoundException{

        return catalogueCrudService.getCatalogueItem(skuNumber);
    }

    /*
    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResourceIdentity> addCatalogueItem(@Valid @RequestBody CatalogueItem catalogueItem){

        Long id = catalogueCrudService.addCatalogueItem(catalogueItem);

        return new ResponseEntity<>(new ResourceIdentity(id), HttpStatus.CREATED);
    }*/

    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<CatalogueItem> addCatalogueItem(@Valid @RequestBody CatalogueItem catalogueItem) throws ResourceNotFoundException {

        Long id = catalogueCrudService.addCatalogueItem(catalogueItem);

        CatalogueItem cat = catalogueCrudService.getCatalogueItem(catalogueItem.getSku());

        return new ResponseEntity<>(cat,
                HttpStatus.CREATED);
    }

    @PutMapping(CatalogueControllerAPIPaths.UPDATE)
    @ResponseStatus(HttpStatus.OK)
    public void updateCatalog(@PathVariable(value = "sku") String skuNumber,
                                                       @Valid @RequestBody CatalogueItem catalogueItem)
        throws ResourceNotFoundException{

        catalogueCrudService.updateCatalogueItem(catalogueItem);
    }

    @DeleteMapping(CatalogueControllerAPIPaths.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCatalogueItem(@PathVariable(value = "sku") String skuNumber) throws ResourceNotFoundException {
        catalogueCrudService.deleteBySku(skuNumber);
    }

}
