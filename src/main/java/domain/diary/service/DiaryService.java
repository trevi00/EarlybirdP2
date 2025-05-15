package domain.diary.service;

import domain.diary.model.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryService {

    void save(Diary diary);

    List<Diary> findAll(String userId);

    Diary findByDate(String userId, LocalDate date);

    void delete(String userId, LocalDate date);
}
