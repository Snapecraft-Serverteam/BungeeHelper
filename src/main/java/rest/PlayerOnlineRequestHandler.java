package rest;

import api.Util;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.md_5.bungee.api.ProxyServer;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PlayerOnlineRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

        if(t.getRequestURI().getQuery() != null && Util.queryToMap(t.getRequestURI().getQuery()).get("player") != null) {
            Map<String, String> params = Util.queryToMap(t.getRequestURI().getQuery());

            JSONObject api = new JSONObject();

            if(ProxyServer.getInstance().getPlayer(params.get("player")) != null) {
                api.put("player", params.get("player"));
                api.put("isOnline", true);
                api.put("server", ProxyServer.getInstance().getPlayer(params.get("player")).getServer().getInfo().getName());

                String response = api.toJSONString();
                t.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                api.put("player", params.get("player"));
                api.put("isOnline", false);
                api.put("server", null);

                String response = api.toJSONString();
                t.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }


        } else {
            String response = "<h1 style='font-family: sans-serif;'>You have to specify the player you want to query!</h1> <br> Please add the GET parameter 'player=' along with the player's name.";
            t.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}
