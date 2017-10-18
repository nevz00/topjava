package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> mealWithExceedList= getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(21,0), 2000);
        for (UserMealWithExceed u : mealWithExceedList){
            System.out.println(u);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> mealWithExceedList = new ArrayList<>();
        int day=0;
        int coloies=0;
        for(int i =0;i<mealList.size();i++){
            coloies+=mealList.get(i).getCalories();
            day = mealList.get(i).getDateTime().getDayOfYear();

            System.out.println(day);
            if (TimeUtil.isBetween(LocalTime.from(mealList.get(i).getDateTime()),startTime,endTime))
                mealWithExceedList.add(new UserMealWithExceed(mealList.get(i).getDateTime(), mealList.get(i).getDescription(), mealList.get(i).getCalories(), coloies>caloriesPerDay));
            try {
                if (mealList.get(i + 1).getDateTime().getDayOfYear() != day) {
                    coloies = 0;
                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Exception e");
            }
        }
        //System.out.println("TODO return filtered list with correctly exceeded field");
        return mealWithExceedList;
    }
}
