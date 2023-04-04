package pl.com.web.shop.domain.item.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest;
import pl.com.web.shop.domain.item.model.outside.ItemDetails;

@Api(value = "Items")
public interface ItemsApi {

    @ApiOperation(
            value = "Activate driver.",
            nickname = "activateDriver",
            notes = "Required permission: driver.admin",
            response = ItemDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"drivers"}
    )
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "OK",
            response = ItemDetails.class
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
            value = {"/shop-domain/items"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    ResponseEntity<ItemDetails> createItem(@ApiParam(value = "",required = true) @RequestBody ItemCreateRequest createRequest);
}
