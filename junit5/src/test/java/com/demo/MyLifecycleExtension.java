package com.demo;

import org.junit.jupiter.api.extension.*;

public class MyLifecycleExtension  implements
        BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        AfterEachCallback,
        AfterAllCallback {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("afterAllCallback");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("afterEachCallback");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("afterTestExecutionCallback");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("beforeAllCallback");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("beforeEachCallback");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("beforeTestExecutionCallback");
    }
}
