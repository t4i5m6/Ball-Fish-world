package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;
import java.awt.geom.Point2D;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class BallWorldController {

    /**
     * The main entry Point2D.Double into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();


        post("/load/:type", (request, response) -> {
            String type  = request.params(":type");
            return gson.toJson(dis.loadPaintObj(request.queryParams("strategies"), type, request.queryParams("switchOrNot")));
        });


        post("/switch", (request, response) -> {
            return gson.toJson(dis.switchStrategy(request.queryParams("strategies")));
        });

        get("/update", (request, response) -> {
            return gson.toJson(dis.updateBallWorld());
        });

        post("/canvas/dims", (request, response) -> {
            dis.setCanvasDims(new Point2D.Double(Integer.parseInt(request.queryParams("height")),
                    Integer.parseInt(request.queryParams("width"))));
            return gson.toJson("set canvas dimensions");
        });

        get("/remove/:strat", (request, response) -> {
            return dis.removeObjects(request.params("strat"));
        });

        get("/clear", (request, response) -> {
            dis.removeAll();
            return gson.toJson("removed all balls in ball world");
        });

        get("/*", ((request, response) -> {
            response.redirect("/");
            return gson.toJson("Returned");
        }));



    }

    /**
     * Get the heroku assigned port number.
     * @return The port number
     */

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
