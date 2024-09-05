package com.oasis.acquiesce.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Component
@RequiredArgsConstructor
public class Restaurant {

    private final Chef chef;

}
