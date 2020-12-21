package com.cataloguesample.cataloguecrud;

import com.cataloguesample.cataloguecrud.model.CatalogueItem;
import com.cataloguesample.cataloguecrud.service.CatalogueCrudService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class CatalogueCrudApplication {
	@Autowired
	private CatalogueCrudService catalogueCrudService;

	public static void main(String[] args) {
		SpringApplication.run(CatalogueCrudApplication.class, args);
	}

	@PostConstruct
	private void loadData(){
		// create a Catalog
		CatalogueItem catalogue = new CatalogueItem((long) 1,"1","1","Initial Name", 1.12,
					121,new Date(),new Date());
		catalogueCrudService.addCatalogueItem(catalogue);


	}

}
