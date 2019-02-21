package com.github.pavelvil;

import com.github.pavelvil.config.TestBasicConfiguration;
import com.github.pavelvil.config.TestJPAConfiguration;
import com.github.pavelvil.repository.TodoRepository;
import com.github.pavelvil.service.TodoService;
import com.github.pavelvil.service.TodoServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestBasicConfiguration.class, TestJPAConfiguration.class,})
@Transactional(value = "testJpaTransactionManager")
@EnableJpaRepositories(basePackageClasses = TodoRepository.class,
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "testJpaTransactionManager")
public abstract class AbstractTest {

    protected static final Long ID = 1L;
    protected static final String LABEL = "Test 1";
    protected static final String NEW_LABEL = "Created by test";

    @Autowired
    private TodoRepository todoRepository;
    protected TodoService todoService;

    @Before
    public void setup() {
        todoService = new TodoServiceImpl(todoRepository);
    }

}

