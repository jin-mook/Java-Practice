package com.example.ssedemo.eventlistener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;

import static org.junit.jupiter.api.Assertions.*;

class EventPublisherWithAwareTest {

    @DisplayName("생성자 테스트")
    @Test
    void construct() {
        // given
        EventPublisherWithAware eventPublisherWithAware = new EventPublisherWithAware();
        // when
        eventPublisherWithAware.publish(new HelloEventDto(new EventEntity("eventName1")));
        // then

    }

}