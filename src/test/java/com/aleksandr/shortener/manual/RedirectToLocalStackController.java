package com.aleksandr.shortener.manual;

import com.aleksandr.test.infrastructure.helper.LocalInfrastructureHelper;
import com.aleksandr.test.manual.BaseRedirectToLocalStackController;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectToLocalStackController extends BaseRedirectToLocalStackController {

    @Autowired
    private LocalInfrastructureHelper localInfrastructureHelper;

    @Override
    protected String getApiGatewayName() {
        return "url-shortener";
    }

    @Override
    protected RequestHandler<AwsProxyRequest, AwsProxyResponse> getApiLambdaHandler() {
        //return new ApiLambdaHandler();
        return null;
    }

    @Override
    protected void afterApiCall(final HttpServletRequest request, final JsonNode response) {
    }

}
