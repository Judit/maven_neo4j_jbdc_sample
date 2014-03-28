Sample web application using Java, Maven and Neo4j JDBC to connect it with GrapheneDB.

### Services needed
To run the sample application you will need an account on the next services:

- [GrapheneDB](http://www.graphenedb.com): offers hosted, reliable and optimized graph databases to power your app.
- [CloudBees](http://www.cloudbees.com): PaaS platform for Java and the JVM.

### CloudBees SDK
To use CloudBees Maven plugin, set environment variables and deploy the application, you need to [install the CloudBees SDK](http://developer.cloudbees.com/bin/view/RUN/BeesSDK#HApplicationCommands).

### Maven configuration
You will need to have [Apache Maven installed](http://maven.apache.org/download.cgi#Installation) to be able to use Maven tools. You could also take a look at [Maven in 5 minutes guide](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) to learn how to run Maven tools.

#### Use Neo4j JDBC Driver
To use Neo4j JDBC Driver it was added to the pom.xml file the next lines:
```xml
  <dependencies>
    <dependency>
      <groupId>org.neo4j</groupId>
      <artifactId>neo4j-jdbc</artifactId>
      <version>2.0.1</version>
    </dependency>
  </dependencies>
  
  <repositories>
    <repository>
      <id>neo4j-releases</id>
      <url>http://m2.neo4j.org/releases/</url>
    </repository>
  </repositories>
```

#### Use CloudBees plugin
As it is explained in the [CloudBees wiki Maven guide](https://wiki.cloudbees.com/bin/view/RUN/MavenGuide), it was added to the pom.xml the next lines:
```xml
  <pluginRepositories>
    <pluginRepository>
      <id>cloudbees-public-release</id>
      <url>http://repository-cloudbees.forge.cloudbees.com/public-release</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </pluginRepository>
  </pluginRepositories>
  
  <build>
    <finalName>myapp</finalName>
    <plugins>
      <plugin>
        <groupId>com.cloudbees</groupId>
        <artifactId>bees-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
          <port>8085</port>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

The port property was added because on the default port *8080* it was running other server.

### Neo4j JDBC configuration
The needed information to connect with GrapheneDB is given by environment variables. You will need to set the following:
```
graphenedb.user // REST USER
graphenedb.password // REST PASSWORD
graphenedb.url // REST URL (e.g.: testdb201.sb01.stations.graphenedb.com:24789)
```
You could find all this information on your GrapheneDB databse connection settings.

To set the environment variables, use [CloudBees SDK tools](http://developer.cloudbees.com/bin/view/RUN/Configuration+Parameters) like:
```
bees config:set -a ACCOUNT/APP_NAME graphenedb.user=REST_USER graphenedb.password=REST_PASSWORD graphenedb.url=REST_URL
```

### Run the application
```
mvn bees:run
```

### Deploy the application to CloudBees

```
mvn package bees:deploy -Dbees.appid=ACCOUNT/APP_NAME -Dbees.message="DEPLOY MESSAGE"
```
If you are subscribed to our EU service - you can use the parameter: -Dbees.apiurl=https://api-eu.cloudbees.com/api to target EU. 
