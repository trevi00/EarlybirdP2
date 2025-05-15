// domain.diary.service.DiaryService.java
package domain.diary.service;

import domain.diary.model.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryService {
    void saveDiary(Diary diary);
    List<Diary> getAllDiaries(String username);
    Diary getDiaryByDate(String username, LocalDate date);
    void deleteDiary(String username, LocalDate date);
}
