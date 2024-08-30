package com.aleksandr.shortener.manual;

import com.aleksandr.test.manual.BaseRedirectToLocalStackController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectToLocalStackController extends BaseRedirectToLocalStackController {

    @Override
    protected String getApiGatewayName() {
        return "url-shortener";
    }

}
