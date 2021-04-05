package com.api.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {
    //private static final Log log = LogFactory.getLog(RestAssuredRequestFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(" Request Method => " + requestSpec.getMethod() + 
        		"\n Request URI => " + requestSpec.getURI() + 
        		"\n Request Header =>\n" + requestSpec.getHeaders() +
        		"\n Request Body => " + requestSpec.getBody() +
        		"\n\n Response Status => "+ response.getStatusLine() + 
        		"\n Response Header =>\n"+ response.getHeaders() + 
        		"\n Response Body => " + response.getBody().prettyPrint());
        System.out.println("-----------------------------------------------------------------------------------------");
        return response;
    }
}