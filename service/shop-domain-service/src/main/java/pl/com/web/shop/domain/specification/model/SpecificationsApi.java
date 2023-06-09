package pl.com.web.shop.domain.specification.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.web.shop.domain.specification.model.outside.SpecificationCreateRequest;
import pl.com.web.shop.domain.specification.model.outside.SpecificationDetails;
import pl.com.web.shop.domain.specification.model.outside.SpecificationUpdateRequest;

import java.util.UUID;

@Api(value = "Specifications")
public interface SpecificationsApi {

    @ApiOperation(
            value = "Create specification.",
            nickname = "createSpecification",
            response = SpecificationDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"specifications"}
    )
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "OK",
            response = SpecificationDetails.class
    ), @ApiResponse(
            code = 400,
            message = "Bad request - the request cannot be handled by the server due to an irregularity perceived as a user's error (e.g. incorrect query syntax).",
            response = Object.class
    ), @ApiResponse(
            code = 403,
            message = "Forbidden - access denied",
            response = Object.class
    ), @ApiResponse(
            code = 404,
            message = "Not found",
            response = Object.class
    )})
    @RequestMapping(
            value = {"/shop-domain/items/{itemId}/specifications"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    ResponseEntity<SpecificationDetails> createSpecification(@ApiParam(value = "Identyfikator obiektu", required = true) @PathVariable("itemId") UUID itemId, @ApiParam(value = "",required = true) @RequestBody SpecificationCreateRequest specificationCreateRequest);

    @ApiOperation(
            value = "Update specification.",
            nickname = "updateSpecification",
            response = SpecificationDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"specifications"}
    )
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "OK",
            response = SpecificationDetails.class
    ), @ApiResponse(
            code = 400,
            message = "Bad request - the request cannot be handled by the server due to an irregularity perceived as a user's error (e.g. incorrect query syntax).",
            response = Object.class
    ), @ApiResponse(
            code = 403,
            message = "Forbidden - access denied",
            response = Object.class
    ), @ApiResponse(
            code = 404,
            message = "Not found",
            response = Object.class
    )})
    @RequestMapping(
            value = {"/shop-domain/items/{itemId}/specifications/{specificationId}"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.PUT}
    )
    ResponseEntity<SpecificationDetails> updateSpecification(@ApiParam(value = "Identyfikator obiektu", required = true) @PathVariable("itemId") UUID itemId, @ApiParam(value = "Identyfikator specyfikacji", required = true) @PathVariable("specificationId") UUID specificationId, @ApiParam(value = "",required = true) @RequestBody SpecificationUpdateRequest updateRequest);
}
