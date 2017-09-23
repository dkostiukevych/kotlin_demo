package com.database.test;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.ClassRule;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

public class BaseTest {

  @ClassRule
  public static PostgreSQLContainer postgre = new PostgreSQLContainer();

  QueryRunner db = db();

  private QueryRunner db(){
    PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setUser("test");
    dataSource.setPassword("test");
    dataSource.setUrl(postgre.getJdbcUrl());

    return new QueryRunner(dataSource);
  }

//  private inline fun <reified T> row(): BeanHandler<T> {
//    return BeanHandler(T::class.java)
//  }
//
//  private inline fun <reified T> rows(): BeanListHandler<T> {
//    return BeanListHandler(T::class.java)
//  }
}
