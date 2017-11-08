package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {
    private MealDao mealDao;

    public MealServlet() {
        this.mealDao = new MealDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealDao.getMeals(), LocalTime.of(7,0), LocalTime.of(20,0),2000);
        req.setAttribute("list", mealWithExceeds);
        RequestDispatcher view = req.getRequestDispatcher("/meal.jsp");
        view.forward(req,resp);


    }

}
