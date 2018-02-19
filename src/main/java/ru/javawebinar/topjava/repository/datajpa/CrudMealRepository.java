package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Override
    @Transactional
    Meal save(Meal meal);


    @Transactional
    @Modifying
    @Query("DELETE from Meal m where m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Optional<Meal> findById(Integer id);

    @Query("select m from Meal m where m.user.id=:userId order by m.dateTime desc")
    List<Meal> findAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("select m from Meal m where m.user.id=:userId and m.dateTime between :startDate and :endDate order by m.dateTime desc")
    List<Meal> getBetween(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);


}
