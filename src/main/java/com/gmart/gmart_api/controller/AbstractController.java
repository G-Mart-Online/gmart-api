package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.config.Response;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    protected ResponseEntity<Response> createDeleteSuccessResponse() {
        Response response = new Response(
                "Product has been deleted successfully.",
                "Success"
        );
        return ResponseEntity.ok(response);
    }


}
