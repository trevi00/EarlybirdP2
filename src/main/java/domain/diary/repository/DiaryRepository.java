package domain.diary.repository;

import domain.diary.model.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository {

    void save(Diary diary);

    List<Diary> findAllByUserId(String userId);

    Diary findByUserIdAndDate(String userId, LocalDate date);

    void deleteByUserIdAndDate(String userId, LocalDate date);
}
