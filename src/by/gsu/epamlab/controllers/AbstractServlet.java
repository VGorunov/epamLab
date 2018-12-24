package by.gsu.epamlab.controllers;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    abstract protected void performTask(HttpServletRequest req,
                                        HttpServletResponse resp)
            throws ServletException, IOException;

    protected final List<String> parseJson(HttpServletRequest req) throws JSONException {
        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) jb.append(line);
        } catch (Exception e) {
            throw new JSONException(e);
        }

        JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
        String someString = jsonObject.getString("Method");
        JSONArray jsonArray = new JSONArray(someString);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
