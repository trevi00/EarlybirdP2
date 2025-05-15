// app/context/EarlyBirdContext.java
package app.context;

import domain.attendance.repository.AttendanceRepository;
import domain.attendance.repository.JdbcAttendanceRepository;
import domain.attendance.service.AttendanceService;
import domain.attendance.service.DefaultAttendanceService;

import domain.bird.model.Bird;
import domain.bird.repository.BirdRepository;
import domain.bird.repository.JdbcBirdRepository;
import domain.bird.service.*;

import domain.diary.repository.DiaryRepository;
import domain.diary.repository.JdbcDiaryRepository;
import domain.diary.service.DiaryService;
import domain.diary.service.DefaultDiaryService;

import domain.point.repository.PointRepository;
import domain.point.repository.JdbcPointRepository;
import domain.point.service.PointService;
import domain.point.service.DefaultPointService;

import domain.todo.repository.JdbcToDoRepository;
import domain.todo.repository.ToDoRepository;
import domain.todo.service.DefaultToDoService;
import domain.todo.service.ToDoService;

import domain.user.repository.JdbcUserRepository;
import domain.user.repository.UserRepository;
import domain.user.service.UserService;

import domain.weather.fetcher.OpenWeatherFetcher;
import domain.weather.fetcher.WeatherFetcher;
import domain.weather.model.InMemoryWeatherCacheManager;
import domain.weather.model.WeatherCacheManager;
import domain.weather.service.DefaultWeatherService;
import domain.weather.service.WeatherService;

import config.DatabaseConfig;

import java.sql.Connection;

public class EarlyBirdContext {
    public final AttendanceService attendanceService;
    public final BirdService birdService;
    public final PointService pointService;
    public final DiaryService diaryService;
    public final ToDoService toDoService;
    public final UserService userService;
    public final WeatherService weatherService;

    public EarlyBirdContext() {
        Connection conn = DatabaseConfig.getConnection();

        // Repository
        AttendanceRepository attendanceRepo = new JdbcAttendanceRepository(conn);
        BirdRepository birdRepo = new JdbcBirdRepository(conn);
        PointRepository pointRepo = new JdbcPointRepository(conn);
        DiaryRepository diaryRepo = new JdbcDiaryRepository(conn);
        ToDoRepository toDoRepo = new JdbcToDoRepository(conn);
        UserRepository userRepo = new JdbcUserRepository(conn);

        // Service
        pointService = new DefaultPointService(pointRepo);
        birdService = new DefaultBirdService(birdRepo, new StageEvolutionPolicy());
        attendanceService = new DefaultAttendanceService(attendanceRepo, pointService, birdService);
        diaryService = new DefaultDiaryService(diaryRepo);
        toDoService = new DefaultToDoService(toDoRepo, pointService);
        userService = new UserService(userRepo);

        WeatherFetcher fetcher = new OpenWeatherFetcher();
        WeatherCacheManager cache = new InMemoryWeatherCacheManager();
        weatherService = new DefaultWeatherService(cache, fetcher);
    }
}
