package com.jdbc;

import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DAO{

  protected Neo4jConnection dbConnection;

  public DAO() throws SQLException {
    final Driver driver = new Driver();
    final String hostPort = System.getProperty("graphenedb.url");
    final Properties props = new Properties();

    props.put("user", System.getProperty("graphenedb.user"));
    props.put("password", System.getProperty("graphenedb.password"));
    props.put("legacy", ""); // Need to work with Neo4j Community Edition 2.0.1

    dbConnection = driver.connect("jdbc:neo4j://" + hostPort, props);
  }


  public ResultSet getFirstNode () throws SQLException {
    final String query = "START root=node(0) RETURN root";

    ResultSet rs = dbConnection.createStatement().executeQuery(query);
    rs.next();
    return rs;
  }
}
