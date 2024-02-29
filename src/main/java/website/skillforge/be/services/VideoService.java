package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateVideoRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.Video;
import website.skillforge.be.repository.ChapterRepository;
import website.skillforge.be.repository.LessonRepository;
import website.skillforge.be.repository.VideoRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    AccountUtil accountUtil;

    public Video createVideo(CreateVideoRequestDTO createVideoRequestDTO){
        Account account = accountUtil.getCurrentAccount();
        Lesson lesson = lessonRepository.findLessonById(createVideoRequestDTO.getLessonId());
        Video video = new Video();
        video.setName(createVideoRequestDTO.getVideoName());
        video.setVideoLink(createVideoRequestDTO.getVideoLink());
        video.setUploadBy(account);
        return videoRepository.save(video);
    }

    public Video updateVideo(Long id, CreateVideoRequestDTO video){
        Account account = accountUtil.getCurrentAccount();
        Video video1 = videoRepository.findVideoById(id);
        if (video1 == null){
            return null;
        }
        video1.setName(video.getVideoName());
        video1.setVideoLink(video.getVideoLink());
        video1.setUploadBy(account);
        return videoRepository.save(video1);
    }

    public Video findVideoById(Long id) {
        return videoRepository.findVideoById(id);
    }

    public Video findVideoByName(String name) {
        return videoRepository.findVideoByName(name);
    }


    public int deleteVideoById(Long id) {
        videoRepository.deleteById(id);
        return 1;
    }

    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }
}
