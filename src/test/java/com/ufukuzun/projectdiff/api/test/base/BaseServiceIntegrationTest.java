package com.ufukuzun.projectdiff.api.test.base;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class BaseServiceIntegrationTest extends BaseSpringTest {
}
