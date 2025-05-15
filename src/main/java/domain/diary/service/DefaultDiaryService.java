// domain.diary.service.DefaultDiaryService.java
package domain.diary.service;

import domain.diary.model.Diary;
import domain.diary.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

public class DefaultDiaryService implements DiaryService {

    private final DiaryRepository repo;

    public DefaultDiaryService(DiaryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void saveDiary(Diary diary) {
        repo.save(diary);
    }

    @Override
    public List<Diary> getAllDiaries(String username) {
        return repo.findAllByUsername(username);
    }

    @Override
    public Diary getDiaryByDate(String username, LocalDate date) {
        return repo.findByUsernameAndDate(username, date);
    }

    @Override
    public void deleteDiary(String username, LocalDate date) {
        repo.deleteByUsernameAndDate(username, date);
    }
}
