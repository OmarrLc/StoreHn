package hn.test.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.test.store.dto.base.GeneralResponseDto;
import hn.test.store.dto.exposure.request.product.AddProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.product.RemoveProductToCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.response.product.AddProductToCustomerOrderRestResponseIDto;
import hn.test.store.dto.exposure.response.product.RemoveProductToCustomerOrderRestResponseDto;
import hn.test.store.service.exposure.IProductService;
import hn.test.store.service.exposure.implementation.IAuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Tag(name = "Product API´s")
@RestController
@RequestMapping("/api/v1/product/")
@Slf4j
public class ProductController {

	@Autowired
	IProductService productService;

	@Autowired
	IAuthenticationServiceImpl authenticationServce;

	@Operation(summary = "Creat All Products", description = "Create all the products needed for the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Boolean.class))) })
	@PostMapping("create")
	public ResponseEntity<Boolean> creatAllProducts() {

		try {
			Boolean result = productService.createAllProducts();

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Add Product", description = "Add product to shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = AddProductToCustomerOrderRestResponseIDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = AddProductToCustomerOrderRestResponseIDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = AddProductToCustomerOrderRestResponseIDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = AddProductToCustomerOrderRestResponseIDto.class))) })
	@PostMapping("add")
	public ResponseEntity<AddProductToCustomerOrderRestResponseIDto> addProductToOrder(
			@RequestBody AddProductToCustomerOrderRestRequestDto request, @RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();

		try {
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
						AddProductToCustomerOrderRestResponseIDto.builder().generalResponse(generalResponse).build());
			}
			AddProductToCustomerOrderRestResponseIDto result = productService.addProductToCustomerOrder(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Remove Product", description = "Remove product to shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RemoveProductToCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = RemoveProductToCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = RemoveProductToCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = RemoveProductToCustomerOrderRestResponseDto.class))) })
	@PostMapping("remove")
	public ResponseEntity<RemoveProductToCustomerOrderRestResponseDto> removeProductToOrder(
			@RequestBody RemoveProductToCustomerOrderRestRequestDto request,
			@RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();

		try {
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
						RemoveProductToCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build());
			}
			RemoveProductToCustomerOrderRestResponseDto result = productService.removeProductToCustomerOrder(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}
}
