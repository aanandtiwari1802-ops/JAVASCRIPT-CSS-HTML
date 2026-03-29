package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.City;

@WebServlet("/cities.do")
public class CityServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int stateId = Integer.parseInt(request.getParameter("state_id"));
        
        ArrayList<City> cities = City.collectAllCities(stateId);

        String json = new Gson().toJson(cities);

        response.getWriter().write(json);
    }
}
