// domain.diary.controller.DiaryController.java
package domain.diary.controller;

import domain.diary.model.Diary;
import domain.diary.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

public class DiaryController {

    private final DiaryService service;

    public DiaryController(DiaryService service) {
        this.service = service;
    }

    public void save(String username, LocalDate date, String weather, String title, String content) {
        Diary diary = new Diary(username, date, weather, title, content);
        service.saveDiary(diary);
    }

    public List<Diary> getAll(String username) {
        return service.getAllDiaries(username);
    }

    public Diary getByDate(String username, LocalDate date) {
        return service.getDiaryByDate(username, date);
    }

    public void delete(String username, LocalDate date) {
        service.deleteDiary(username, date);
    }
}
