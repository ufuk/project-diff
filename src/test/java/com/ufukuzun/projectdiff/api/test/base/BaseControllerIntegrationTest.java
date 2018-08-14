package com.ufukuzun.projectdiff.api.test.base;

import com.ufukuzun.projectdiff.api.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@MockBean(classes = {
        DataCollectService.class
})
public abstract class BaseControllerIntegrationTest extends BaseSpringTest {

    @Autowired
    private MockMvc mockMvc;

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

}
