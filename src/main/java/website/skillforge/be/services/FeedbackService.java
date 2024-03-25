package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateFeedbackDto;
import website.skillforge.be.entities.courses.Feedback;
import website.skillforge.be.repository.CourseRepository;
import website.skillforge.be.repository.FeedbackRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private AccountUtil accountUtil;
    @Autowired
    private CourseRepository courseRepository;

    public Feedback createFeedback(CreateFeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setContent(feedbackDto.getContent());
        feedback.setStar(feedbackDto.getStar());
        feedback.setCreatedDate(new Date());
        feedback.setAccount(accountUtil.getCurrentAccount());
        feedback.setCourse(courseRepository.findCourseById(feedbackDto.getCourseId()));
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByCourseId(long id) {
        return feedbackRepository.findFeedbackByCourseId(id);
    }

    public Feedback getFeedbackById(long id) {
        return feedbackRepository.findFeedbackById(id);
    }

    public List<Feedback> getFeedbackByAccountId(long id) {
        return feedbackRepository.findFeedbackByAccountId(id);
    }
}
