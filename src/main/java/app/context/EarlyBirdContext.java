// app.context.EarlyBirdContext.java
package app.context;

import domain.attendance.controller.AttendanceController;
import domain.attendance.repository.AttendanceRepository;
import domain.attendance.repository.JdbcAttendanceRepository;
import domain.attendance.service.AttendanceService;
import domain.attendance.service.AttendanceStatsService;

import domain.bird.controller.BirdController;
import domain.bird.model.Bird;
import domain.bird.repository.BirdRepository;
import domain.bird.repository.JdbcBirdRepository;
import domain.bird.service.BirdService;
import domain.bird.service.StageEvolutionPolicy;

import domain.diary.controller.DiaryController;
import domain.diary.repository.DiaryRepository;
import domain.diary.repository.JdbcDiaryRepository;
import domain.diary.service.DiaryService;

import domain.todo.controller.ToDoController;
import domain.todo.repository.ToDoRepository;
import domain.todo.repository.JdbcToDoRepository;
import domain.todo.service.ToDoService;

import domain.user.controller.UserController;
import domain.user.repository.UserRepository;
import domain.user.repository.JdbcUserRepository;
import domain.user.service.UserService;

import domain.weather.controller.WeatherController;
import domain.weather.fetcher.OpenWeatherFetcher;
import domain.weather.repository.InMemoryWeatherCacheManager;
import domain.weather.repository.WeatherCacheManager;
import domain.weather.service.WeatherFetcher;
import domain.weather.service.WeatherService;

import domain.point.PointManager;
import ui.message.BirdMessageManager;
import ui.message.BirdMessageProvider;

import config.DatabaseConfig;

import java.sql.Connection;

public class EarlyBirdContext {

    public final UserController userController;
    public final AttendanceController attendanceController;
    public final BirdController birdController;
    public final ToDoController toDoController;
    public final DiaryController diaryController;
    public final WeatherController weatherController;
    public final BirdMessageManager birdMessageManager;

    public EarlyBirdContext() {
        Connection conn = DatabaseConfig.getConnection();

        // 공통 인프라
        PointManager pointManager = new PointManager();
        Bird bird = new Bird();
        BirdMessageProvider messageProvider = new BirdMessageProvider();
        BirdRepository birdRepo = new JdbcBirdRepository(conn);
        BirdService birdService = new BirdService(new StageEvolutionPolicy());

        // 사용자
        UserRepository userRepo = new JdbcUserRepository(conn);
        UserService userService = new UserService(userRepo);
        this.userController = new UserController(userService);

        // 출석
        AttendanceRepository attendanceRepo = new JdbcAttendanceRepository(conn);
        AttendanceService attendanceService = new AttendanceService(attendanceRepo, pointManager);
        AttendanceStatsService statsService = new AttendanceStatsService(attendanceRepo);
        this.attendanceController = new AttendanceController(attendanceService, statsService);

        // 새
        this.birdController = new BirdController(bird, birdService, birdRepo, pointManager);

        // ToDo
        ToDoRepository toDoRepo = new JdbcToDoRepository(conn);
        ToDoService toDoService = new ToDoService(toDoRepo, pointManager);
        this.toDoController = new ToDoController(toDoService);

        // Diary
        DiaryRepository diaryRepo = new JdbcDiaryRepository(conn);
        DiaryService diaryService = new DiaryService(diaryRepo);
        this.diaryController = new DiaryController(diaryService);

        // Weather
        WeatherCacheManager cache = new InMemoryWeatherCacheManager();
        WeatherFetcher fetcher = new OpenWeatherFetcher();
        WeatherService weatherService = new WeatherService(cache, fetcher);
        this.weatherController = new WeatherController(weatherService);

        // 메시지
        this.birdMessageManager = new BirdMessageManager(bird, messageProvider, null); // UI에서 displayer 주입
    }
}
