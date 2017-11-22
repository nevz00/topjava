package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id,userId),id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id,userId),id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return (List<Meal>) repository.getAll(userId);
    }

    @Override
    public Meal update(Meal meal, int userId) {
        return checkNotFoundWithId(repository.save(meal,userId),meal.getId());
    }

    @Override
    public Meal create(Meal meal, int userId) {
        return repository.save(meal,userId);
    }

    @Override
    public List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime,endDateTime,userId);
    }
}