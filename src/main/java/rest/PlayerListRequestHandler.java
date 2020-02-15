package rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerListRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        JSONObject api = new JSONObject();
        for(Map.Entry<String, ServerInfo> server : ProxyServer.getInstance().getServers().entrySet()) {
            String servername = server.getValue().getName();

            List<String> playerlist = new ArrayList<>();
            ProxyServer.getInstance().getServerInfo(server.getValue().getName()).getPlayers().stream().map(CommandSender::getName).forEach(playerlist::add);

            api.put(servername, playerlist);
        }

        String response = api.toJSONString();
        t.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
