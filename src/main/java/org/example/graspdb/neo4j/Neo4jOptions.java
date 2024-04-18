package org.example.graspdb.neo4j;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.JsonObject;
import org.example.graspdb.DBMSSpecificOptions;
import org.example.graspdb.cypher.dsl.IQueryGenerator;


@Parameters(separators = "=", commandDescription = "Neo4J (default port: " + Neo4jOptions.DEFAULT_PORT
        + ", default host: " + Neo4jOptions.DEFAULT_HOST)
public class Neo4jOptions implements DBMSSpecificOptions {
    //本地访问
    public static final String DEFAULT_HOST = "localhost";
    //远程访问
//    public static final String DEFAULT_HOST = "172.24.65.212";
    //TODO 第一个数据库的端口号
    public static final int DEFAULT_PORT = 7687; //todo 改

    public static Neo4jOptions parseOptionFromFile(JsonObject jsonObject){
        Neo4jOptions options = new Neo4jOptions();
        if(jsonObject.has("host")){
            options.host = jsonObject.get("host").getAsString();
        }
        if(jsonObject.has("port")){
            options.port = jsonObject.get("port").getAsInt();
        }
        if(jsonObject.has("username")){
            options.username = jsonObject.get("username").getAsString();
        }
        if(jsonObject.has("password")){
            options.password = jsonObject.get("password").getAsString();
        }
        if(jsonObject.has("use_jdbc")){
            options.useJDBC = jsonObject.get("use_jdbc").getAsBoolean();
        }
        if(jsonObject.has("proxy_port")){
            options.proxyPort = jsonObject.get("proxy_port").getAsInt();
        }
        return options;
    }


    public String getHost() {
            return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
            return port;
    }
    @Parameter(names = "--host")
    public String host = DEFAULT_HOST;

    @Parameter(names = "--port")
    public int port = DEFAULT_PORT;

    @Parameter(names = "--username")
    public String username = "neo4j";

    @Parameter(names = "--password")
    public String password = "123abc...";

    @Parameter(names = "--use_jdbc")
    public boolean useJDBC = false;

    @Parameter(names = "--proxy_port")
    public int proxyPort = 0;

    @Override
    public IQueryGenerator getQueryGenerator() {
        return null;
    }

}
