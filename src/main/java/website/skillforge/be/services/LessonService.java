package website.skillforge.be.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import website.skillforge.be.dto.createDTO.CreateLessonRequestDTO;
import website.skillforge.be.dto.createDTO.GetAllLessonResponse;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.quiz.Quiz;
import website.skillforge.be.entities.quiz.QuizAnswer;
import website.skillforge.be.entities.quiz.QuizQuestion;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.quizRepo.QuizAnswerRepository;
import website.skillforge.be.repository.quizRepo.QuizQuestionRepository;
import website.skillforge.be.repository.quizRepo.QuizRepository;
import website.skillforge.be.services.quiz.QuizService;
import website.skillforge.be.util.AccountUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizAnswerRepository quizAnswerRepository;
    @Autowired
    private QuizService quizService;
    @Autowired
    AccountUtil accountUtil;

    public Quiz createQuiz(String url) {
        MultipartFile multipartFile = convert(url);
        List<String> data = new ArrayList<>();
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setCreatedDate(new Date());
        quiz.setQuizQuestion(quizQuestions);
        quizRepository.save(quiz);
        try (Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();
                QuizQuestion quizQuestion = new QuizQuestion();
                List<QuizAnswer> answers = new ArrayList<>();
                quizQuestion.setQuizAnswers(answers);
                quizQuestion.setQuiz(quiz);
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    data.add(cell.toString());
                    if (cellIndex == 0) {
                        quizQuestion.setQuestionNumber(cell.toString());
                    }
                    if (cellIndex == 1) {
                        quizQuestion.setQuestionContent(cell.toString());
                        quizQuestionRepository.save(quizQuestion);
                    }
                    if (cellIndex == 2 || cellIndex == 3 || cellIndex == 4 || cellIndex == 5) {
                        QuizAnswer answer = new QuizAnswer();
                        answer.setAnswerContent(cell.toString());
                        answers.add(answer);
                        answer.setQuizQuestion(quizQuestion);
                        quizAnswerRepository.save(answer);
                    }
                    if (cellIndex == 6) {
                        String res = cell.toString();
                        switch (res) {
                            case "A":
                                answers.get(0).setTrue(true);
                                quizAnswerRepository.save(answers.get(0));
                                break;
                            case "B":
                                answers.get(1).setTrue(true);
                                quizAnswerRepository.save(answers.get(1));
                                break;
                            case "C":
                                answers.get(2).setTrue(true);
                                quizAnswerRepository.save(answers.get(2));
                                break;
                            case "D":
                                answers.get(3).setTrue(true);
                                quizAnswerRepository.save(answers.get(3));
                                break;
                            default:
                                break;
                        }
                    }

                    cellIndex++;
                }
                quizQuestions.add(quizQuestion);
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return quiz;
    }

    public static MultipartFile convert(String url) {
        try {
            // Open connection to URL
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read from connection input stream
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] content = byteArrayOutputStream.toByteArray();

            // Extract file name from URL
            String fileName = url.substring(url.lastIndexOf("/") + 1);

            // Create MultipartFile
            MultipartFile result = new MockMultipartFile(fileName, fileName, "application/octet-stream", content);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lesson createLesson(CreateLessonRequestDTO createLessonRequestDTO) {
        Account account = accountUtil.getCurrentAccount();
        Date date = new Date();
        Chapter chapter = chapterRepository.findChapterById(createLessonRequestDTO.getChapter_id());
        Lesson lesson = new Lesson();
        Quiz quiz = createQuiz(createLessonRequestDTO.getQuiz());
        quiz.setLesson(lesson);
        lesson.setName(createLessonRequestDTO.getName());
        lesson.setVideoLink(createLessonRequestDTO.getVideoLink());
        lesson.setDescription(createLessonRequestDTO.getDescription());
        lesson.setCreatedDate(date);
        lesson.setLastUpdatedDate(date);
        lesson.setChapter(chapter);
        lesson.setCreateBy(account);
        lesson.setQuiz(quiz);
        return lessonRepository.save(lesson);
    }

    public Lesson findLessonById(Long id) {
        return lessonRepository.findLessonById(id);
    }

    public Lesson findLessonByName(String name) {
        return lessonRepository.findLessonByName(name);
    }


    public int deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
        return 1;
    }

    public Lesson updateLesson(Long id, CreateLessonRequestDTO lesson) {
        Lesson lesson1 = lessonRepository.findLessonById(id);
        if (lesson1 == null) {
            return null;
        }
        Date date = new Date();
        lesson1.setName(lesson.getName());
        lesson1.setDescription(lesson.getDescription());
        lesson1.setVideoLink(lesson.getVideoLink());
        lesson1.setLastUpdatedDate(date);
        return lessonRepository.save(lesson1);
    }

    public List<Lesson> UpdateLessonByChapterId(Long id, CreateLessonRequestDTO updateLessonDTO) {
        List<Lesson> lessons = lessonRepository.findLessonByChapter_id(id);
        if (lessons == null) {
            return null;
        }
        Date date = new Date();
        for (int i = 0; i < lessons.size(); i++) {
            lessons.get(i).setName(updateLessonDTO.getName());
            lessons.get(i).setDescription(updateLessonDTO.getDescription());
            lessons.get(i).setVideoLink(updateLessonDTO.getVideoLink());
            lessons.get(i).setLastUpdatedDate(date);
        }
        return lessonRepository.saveAll(lessons);

    }


    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public List<Lesson> getAllLessonByChapterId(Long id) {
        return lessonRepository.findLessonByChapter_id(id);
    }

    public List<GetAllLessonResponse> getAllLessonDTOByChapterId(Long id) {
        List<Lesson> lessons = lessonRepository.findLessonByChapter_id(id);
        List<GetAllLessonResponse> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            GetAllLessonResponse lessonDTO = new GetAllLessonResponse();
            lessonDTO.setId(lesson.getId());
            lessonDTO.setName(lesson.getName());
            lessonDTO.setDescription(lesson.getDescription());
            lessonDTO.setVideoLink(lesson.getVideoLink());
            lessonDTO.setChapter_id(lesson.getChapter().getId());
            lessonDTOs.add(lessonDTO);
        }
        return lessonDTOs;
    }

}
