package domain.diary.service;

import domain.diary.model.Diary;
import domain.diary.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

public class DefaultDiaryService implements DiaryService {

    private final DiaryRepository repository;

    public DefaultDiaryService(DiaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Diary diary) {
        repository.save(diary);
    }

    @Override
    public List<Diary> findAll(String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Diary findByDate(String userId, LocalDate date) {
        return repository.findByUserIdAndDate(userId, date);
    }

    @Override
    public void delete(String userId, LocalDate date) {
        repository.deleteByUserIdAndDate(userId, date);
    }
}
