package ru.javawebinar.topjava.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @Autowired
    MealService mealService;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request){
       super.delete(getId(request));
       return "redirect:/meals";
    }
    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model){
        model.addAttribute("meal", super.get(getId(request)));
        return "mealForm";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("meal",super.create(new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "",1000)));
        return "mealForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request){
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty())
            super.create(meal);
            else
                super.update(meal, getId(request));
        return "redirect:/meals";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model){
        LocalDate localDateStart = parseLocalDate(request.getParameter("startDate"));
        LocalDate localDateEnd = parseLocalDate(request.getParameter("endDate"));
        LocalTime localTimeStart = parseLocalTime(request.getParameter("startTime"));
        LocalTime localTimeEnd = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meal",super.getBetween(localDateStart,localTimeStart,localDateEnd,localTimeEnd));
        return "meals";

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
