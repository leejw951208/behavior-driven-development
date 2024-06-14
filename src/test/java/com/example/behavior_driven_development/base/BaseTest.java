package com.example.behavior_driven_development.base;

import org.junit.jupiter.api.Disabled;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@Disabled // 클래스 또는 클래스에 정의된 메소드를 실행하지 않기 위해 선언
@ActiveProfiles("test")
public class BaseTest {
}
