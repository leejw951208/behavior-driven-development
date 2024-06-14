package com.example.behavior_driven_development.base;

import com.example.behavior_driven_development.common.config.JpaAuditingConfig;
import com.example.behavior_driven_development.config.TestQueryDslConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(value = {JpaAuditingConfig.class, TestQueryDslConfig.class})
public class BaseUnitDatabaseTest extends BaseDatabaseTest {
}
