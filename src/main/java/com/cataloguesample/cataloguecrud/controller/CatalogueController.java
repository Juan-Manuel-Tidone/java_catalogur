package com.cataloguesample.cataloguecrud.controller;

import com.cataloguesample.cataloguecrud.service.CatalogueCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CatalogueController {

    private CatalogueCrudService catalogueCrudService;

}
