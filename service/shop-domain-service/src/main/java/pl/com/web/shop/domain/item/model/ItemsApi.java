package pl.com.web.shop.domain.item.model;

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
import pl.com.web.shop.domain.item.model.outside.ItemDetails;
import pl.com.web.shop.domain.item.model.outside.ItemCreateRequest;
import pl.com.web.shop.domain.item.model.outside.ItemUpdateRequest;

import java.util.UUID;

@Api(value = "Items")
public interface ItemsApi {

    @ApiOperation(
            value = "Create item.",
            nickname = "createItem",
            response = ItemDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"items"}
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

    @ApiOperation(
            value = "Update item.",
            nickname = "updateItem",
            response = ItemDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"items"}
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
            value = {"/shop-domain/items/{itemId}"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.PUT}
    )
    ResponseEntity<ItemDetails> updateItem(@ApiParam(value = "Identyfikator obiektu", required = true) @PathVariable("itemId") UUID itemId, @ApiParam(value = "",required = true) @RequestBody ItemUpdateRequest updateRequestRequest);

    @ApiOperation(
            value = "Get item.",
            nickname = "getItem",
            response = ItemDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"items"}
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
            value = {"/shop-domain/items/{itemId}"},
            produces = {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<ItemDetails> getItem(@ApiParam(value = "Identyfikator obiektu", required = true) @PathVariable("itemId") UUID itemId);

    @ApiOperation(
            value = "Get item.",
            nickname = "getItem",
            response = ItemDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"items"}
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
            value = {"/shop-domain/items/{itemId}"},
            produces = {"application/json"},
            method = {RequestMethod.DELETE}
    )
    ResponseEntity<Void> deleteItem(@ApiParam(value = "Identyfikator obiektu", required = true) @PathVariable("itemId") UUID itemId);
}
