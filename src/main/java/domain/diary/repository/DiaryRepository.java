// domain.diary.repository.DiaryRepository.java
package domain.diary.repository;

import domain.diary.model.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository {
    void save(Diary diary);
    List<Diary> findAllByUsername(String username);
    Diary findByUsernameAndDate(String username, LocalDate date);
    void deleteByUsernameAndDate(String username, LocalDate date);
}
