package com.database.test;

import org.junit.ClassRule;
import org.testcontainers.containers.PostgreSQLContainer;

public class BaseTest {

  @ClassRule
  public static PostgreSQLContainer postgre = new PostgreSQLContainer();
}
