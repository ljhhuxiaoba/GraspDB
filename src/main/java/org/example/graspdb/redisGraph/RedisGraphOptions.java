package org.example.graspdb.redisGraph;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.JsonObject;
import org.example.graspdb.DBMSSpecificOptions;
import org.example.graspdb.cypher.dsl.IQueryGenerator;


@Parameters(separators = "=", commandDescription = "RedisGraph (default port: " + RedisGraphOptions.DEFAULT_PORT
        + ", default host: " + RedisGraphOptions.DEFAULT_HOST)
public class RedisGraphOptions implements DBMSSpecificOptions {

//    public static final String DEFAULT_HOST = "redis-18900.c14.us-east-1-2.ec2.cloud.redislabs.com";
    public static final String DEFAULT_HOST = "localhost";
//    public static final int DEFAULT_PORT = 18900; //todo 改
    public static final int DEFAULT_PORT = 6380; //todo 改

    public static RedisGraphOptions parseOptionFromFile(JsonObject jsonObject){
        RedisGraphOptions options = new RedisGraphOptions();
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
        if(jsonObject.has("restart-command")){
            options.restartCommand = jsonObject.get("restart-command").getAsString();
        }
        return options;
    }

    @Parameter(names = "--restart-command")
    public String restartCommand = "";


    @Parameter(names = "--host")
    public String host = DEFAULT_HOST;

    @Parameter(names = "--port")
    public int port = DEFAULT_PORT;

    @Parameter(names = "--username")
    public String username = "redis";

    @Parameter(names = "--password")
    public String password = "123abc...";

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
    public void setPort(int port) {
        this.port=port;
    }


    @Override
    public IQueryGenerator getQueryGenerator() {
        return null;
    }

}
