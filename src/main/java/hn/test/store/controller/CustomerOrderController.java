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
import hn.test.store.dto.exposure.request.customerOrder.CreateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.PayCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.UpdateCustomerOrderRestRequestDto;
import hn.test.store.dto.exposure.request.customerOrder.ViewCustomerOrderDetailRestRequestDto;
import hn.test.store.dto.exposure.response.customerOrder.CreateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.PayCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.UpdateCustomerOrderRestResponseDto;
import hn.test.store.dto.exposure.response.customerOrder.ViewCustomerOrderDetailRestResponseDto;
import hn.test.store.service.exposure.ICustomerOrderService;
import hn.test.store.service.exposure.implementation.IAuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Tag(name = "Customer Order API´s")
@RestController
@RequestMapping("/api/v1/customerOrder/")
@Slf4j
public class CustomerOrderController {

	@Autowired
	ICustomerOrderService customerOrderService;

	@Autowired
	IAuthenticationServiceImpl authenticationServce;

	@Operation(summary = "Create Customer Order", description = "Create customer order for the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CreateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = CreateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = CreateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = CreateCustomerOrderRestResponseDto.class))) })
	@PostMapping("create")
	public ResponseEntity<CreateCustomerOrderRestResponseDto> createCustomerOrder(
			@RequestBody CreateCustomerOrderRestRequestDto request, @RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();

		try {
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
						.body(CreateCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build());
			}

			CreateCustomerOrderRestResponseDto result = customerOrderService.createCustomerOrder(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Pay Customer Order", description = "Pay customer order for the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PayCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = PayCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = PayCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = PayCustomerOrderRestResponseDto.class))) })
	@PostMapping("pay")
	public ResponseEntity<PayCustomerOrderRestResponseDto> payCustomerOrder(
			@RequestBody PayCustomerOrderRestRequestDto request, @RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();
		
		try {
			
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
						.body(PayCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build());
			}
			
			PayCustomerOrderRestResponseDto result = customerOrderService.payCustomerOrder(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Update Customer Order", description = "Update customer order for the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UpdateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = UpdateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = UpdateCustomerOrderRestResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = UpdateCustomerOrderRestResponseDto.class))) })
	@PostMapping("update")
	public ResponseEntity<UpdateCustomerOrderRestResponseDto> updateCustomerOrder(
			@RequestBody UpdateCustomerOrderRestRequestDto request, @RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();
		
		try {
			
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
						.body(UpdateCustomerOrderRestResponseDto.builder().generalResponse(generalResponse).build());
			}
			
			UpdateCustomerOrderRestResponseDto result = customerOrderService.updateCustomerOrder(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "View Customer Order Detail", description = "View customer order detail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ViewCustomerOrderDetailRestResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ViewCustomerOrderDetailRestResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ViewCustomerOrderDetailRestResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ViewCustomerOrderDetailRestResponseDto.class))) })
	@PostMapping("view")
	public ResponseEntity<ViewCustomerOrderDetailRestResponseDto> viewCustomerOrder(
			@RequestBody ViewCustomerOrderDetailRestRequestDto request, @RequestHeader Map<String, String> headers) {

		GeneralResponseDto generalResponse = new GeneralResponseDto();
		
		try {
			
			String token = headers.get("authorization");
			generalResponse = authenticationServce.validateToken(token);

			if (!generalResponse.getResponseCode().equals("00")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
						.body(ViewCustomerOrderDetailRestResponseDto.builder().generalResponse(generalResponse).build());
			}
			
			ViewCustomerOrderDetailRestResponseDto result = customerOrderService.viewCuestomerOrderDetail(request);

			return ResponseEntity.ok(result);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return ResponseEntity.internalServerError().build();
		}
	}
}
