package com.aleksandr.shortener.integration;

import com.aleksandr.shortener.common.TestConfiguration;
import com.aleksandr.shortener.presentation.ShortUrlController;
import com.aleksandr.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfiguration.class)
class ShortUrlControllerTest extends BaseTest {

    @Autowired
    private ShortUrlController controller;

    @Test
    void testGetLongUrl() {
    }


}
