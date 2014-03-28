package com.jdbc;

import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DAO{

  public DAO() {
  }

  protected ResultSet rs;

  public ResultSet getFirstNode () {
    final Driver driver = new Driver();
    final String hostPort = System.getProperty("graphenedb.url");
    final String query = "START root=node(0) RETURN root";
    final Properties props = new Properties();

    props.put("user", System.getProperty("graphenedb.user"));
    props.put("password", System.getProperty("graphenedb.password"));
    props.put("legacy", ""); // Need to work with Neo4j Community Edition 2.0.1

    try {
      Neo4jConnection conn = driver.connect("jdbc:neo4j://" + hostPort, props);
      rs = conn.createStatement().executeQuery(query);
      rs.next();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }
}
