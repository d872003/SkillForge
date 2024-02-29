package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateVideoRequestDTO;
import website.skillforge.be.entities.Video;
import website.skillforge.be.services.VideoService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class VideoController {
    @Autowired
    VideoService videoService;

    @PostMapping("/video")
    public ResponseEntity createVideo(@RequestBody CreateVideoRequestDTO createVideoRequestDTO) {
        Video video = videoService.createVideo(createVideoRequestDTO);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id) {
        videoService.deleteVideoById(id);
        return ResponseEntity.ok("Lesson deleted successfully");
    }

    @PutMapping("/video/{id}")
    public ResponseEntity updateVideo(@PathVariable Long id, @RequestBody CreateVideoRequestDTO updateVideoDTO) {
        Video video = videoService.updateVideo(id, updateVideoDTO);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/video")
    public ResponseEntity getAllVideo() {
        return ResponseEntity.ok(videoService.getAllVideo());
    }

    @GetMapping("/video/{id}")
    public ResponseEntity getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.findVideoById(id));
    }

    @GetMapping("/video/{name}")
    public ResponseEntity getVideoByName(@PathVariable String name) {
        return ResponseEntity.ok(videoService.findVideoByName(name));
    }
}
