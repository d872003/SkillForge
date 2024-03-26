package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.ChartRequest;
import website.skillforge.be.dto.createDTO.ChartResponse;
import website.skillforge.be.entities.courses.CourseEnrollment;
import website.skillforge.be.repository.CourseEnrollmentRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ChartService {
    @Autowired
    CourseEnrollmentRepository enrollRepository;

    public ChartResponse getMonthlyChart(ChartRequest request) {
        ChartResponse response = new ChartResponse();
        List<Integer> numOfStudents = new ArrayList<>();
        List<Double> revenue = new ArrayList<>();
        List<CourseEnrollment> courseEnrollments = enrollRepository.findAll();
        List<Date> dates = new ArrayList<>();
        List<Integer> months = new ArrayList<>();
        for (CourseEnrollment courseEnrollment : courseEnrollments) {
            dates.add(courseEnrollment.getStartDate());
            int month = courseEnrollment.getStartDate().getMonth();
            months.add(++month);
        }
        List<String> monthYearNames = new ArrayList<>();
        Year year = Year.of(request.getYear());
        Month startMonth = Month.of(request.getMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.forLanguageTag("vi-VN"));

        for (int i = 0; i < request.getInterval(); i++) {
            YearMonth previousYearMonth = YearMonth.of(year.getValue(), startMonth.minus(i));
            String formattedMonthYear = previousYearMonth.format(formatter);
            monthYearNames.add(formattedMonthYear);
            int count = 0;
            for (int j = 0; j < months.size(); j++) {
                if (months.get(j) == previousYearMonth.getMonthValue()) {
                    count++;
                }
            }
            numOfStudents.add(count);
            count = 0;
        }

        Collections.reverse(monthYearNames);
        Collections.reverse(numOfStudents);
        response.setLabels(monthYearNames);
        response.setNumOfStudents(numOfStudents);

        return response;
    }

    public static List<String> getPreviousMonths(int inputMonth, int inputYear) {
        List<String> monthYearNames = new ArrayList<>();
        Year year = Year.of(inputYear);
        Month startMonth = Month.of(inputMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.forLanguageTag("vi-VN"));

        for (int i = 0; i < 3; i++) {
            YearMonth previousYearMonth = YearMonth.of(year.getValue(), startMonth.minus(i));
            String formattedMonthYear = previousYearMonth.format(formatter);
            monthYearNames.add(formattedMonthYear);
        }

        Collections.reverse(monthYearNames); // Sắp xếp ngược lại để từ tháng xa đến gần
        return monthYearNames;
    }
}
