package hn.test.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.test.store.service.exposure.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Tag(name = "Product Service")
@RestController
@RequestMapping
@Slf4j
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@Operation(summary = "Creat All Products", description = "Create all the products needed for the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Boolean.class))) })
	@PostMapping("/api/v1/product/create")
	public ResponseEntity<Boolean> creatAllProducts(){
		
		try {
			Boolean result = productService.createAllProducts();

			return ResponseEntity.ok(result);
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

}
