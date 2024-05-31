package com.example.behavior_driven_development.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
/*@ContextConfiguration(classes = {TestContainerConfig.class, TestObjectMapperConfig.class})*/
@Disabled // 클래스 또는 클래스에 정의된 메소드를 실행하지 않기 위해 선언
@AutoConfigureMockMvc // SpringBootTest환경에서 MockMvc를 사용하기 위해 선언
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional // 테스트 코드에서 Transaction이 발생할 경우 해당 데이터를 롤백하기 위해 선언
public class BaseIntegrationTest extends BaseTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
}
