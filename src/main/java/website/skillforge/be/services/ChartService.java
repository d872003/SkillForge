package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.ChartRequest;
import website.skillforge.be.dto.createDTO.ChartResponse;
import website.skillforge.be.entities.accounts.Transactions;
import website.skillforge.be.entities.courses.CourseEnrollment;
import website.skillforge.be.enums.Role;
import website.skillforge.be.repository.CourseEnrollmentRepository;
import website.skillforge.be.repository.TransactionRepository;

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
    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Retrieves monthly chart data based on the given request.
     *
     * @param request The chart request containing year, month, and interval information.
     * @return The chart response object with labels, number of students, and revenue data.
     */
    public ChartResponse getMonthlyChart(ChartRequest request) {
        // Initialize the response object and lists to store data
        ChartResponse response = new ChartResponse();
        List<Integer> numOfStudents = new ArrayList<>();
        List<Double> revenue = new ArrayList<>();

        // Retrieve all course enrollments and populate the months list
        List<CourseEnrollment> courseEnrollments = enrollRepository.findAll();
        List<Integer> months = new ArrayList<>();
        for (CourseEnrollment courseEnrollment : courseEnrollments) {
            months.add(courseEnrollment.getStartDate().getMonth() + 1);
        }

        // Retrieve all transactions and populate the transactionMonths list
        List<Transactions> transactions = transactionRepository.findTransactionsByTo_Account_role(Role.ADMIN);
        HashMap<Long, Integer> transactionMonths = new HashMap<>();
        for (Transactions transaction : transactions) {
            transactionMonths.put(transaction.getId(), transaction.getDate().getMonth() + 1);
        }

        // Set up year, startMonth, and DateTimeFormatter for formatting
        Year year = Year.of(request.getYear());
        Month startMonth = Month.of(request.getMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.forLanguageTag("vi-VN"));

        List<String> monthYearNames = new ArrayList<>();
        for (int i = 0; i < request.getInterval(); i++) {
            // Get the previous year and month, format it, and add to the list of monthYearNames

            YearMonth previousYearMonth = YearMonth.of(year.getValue(), startMonth.minus(i));

            String formattedMonthYear = previousYearMonth.format(formatter);
            monthYearNames.add(formattedMonthYear.toUpperCase());

            int count = 0;
            for (Integer month : months) {
                // Count the number of students enrolled in the previous year and month
                if (month == previousYearMonth.getMonthValue()) {
                    count++;
                }
            }
            numOfStudents.add(count);

            double total = 0;
            for (Map.Entry<Long, Integer> hashMapEntry : transactionMonths.entrySet()) {
                Long transactionId = hashMapEntry.getKey();
                Integer month = hashMapEntry.getValue();
                // Calculate the total revenue for the previous year and month
                if (month == previousYearMonth.getMonthValue()) {
                    Transactions transaction = transactionRepository.findTransactionsById(transactionId);
                    total += transaction.getMoney();
                }
            }
            revenue.add(total);
            if (startMonth.getValue() - i == 1) {
                year = year.minusYears(1);
            }
        }
        // Reverse the lists for correct chronological order
        Collections.reverse(monthYearNames);
        Collections.reverse(numOfStudents);
        Collections.reverse(revenue);

        // Set the data in the response object and return it
        response.setLabels(monthYearNames);
        response.setNumOfStudents(numOfStudents);
        response.setRevenue(revenue);

        return response;
    }

}
