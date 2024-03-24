package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateChapterRequestDTO;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.services.ChapterService;

import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class ChapterController {
    @Autowired
    private ChapterService chapterService;


    @PostMapping("/chapter")
    public ResponseEntity<?> createChapter(@RequestBody CreateChapterRequestDTO createChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.CreateChapter(createChapterRequestDTO));
    }

    @PostMapping("/chapters")
    public ResponseEntity<?> createChapters(@RequestBody List<CreateChapterRequestDTO> createChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.CreateChapters(createChapterRequestDTO));
    }

    @DeleteMapping("/chapter/{id}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long id) {
        chapterService.DeleteChapterById(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @PutMapping("/chapter/{id}")
    public ResponseEntity<?> updateChapter(@PathVariable Long id, @RequestBody CreateChapterRequestDTO updateChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.UpdateChapterById(id, updateChapterRequestDTO));
    }

    @PutMapping("/chapters")
    public ResponseEntity<?> updateChapters(@RequestParam List<Long> id, @RequestBody List<CreateChapterRequestDTO> updateChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.UpdateChaptersById(id, updateChapterRequestDTO));
    }

    @PutMapping("/chapters/courseId")
    public ResponseEntity<?> UpdateChaptersByCourseId(@RequestParam Long id, @RequestBody CreateChapterRequestDTO updateChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.UpdateChaptersByCourseId(id, updateChapterRequestDTO));
    }


    @GetMapping("/chapter/{id}")
    public ResponseEntity<?> getChapterById(@PathVariable Long id) {
        return ResponseEntity.ok(chapterService.GetChapterById(id));
    }

    @GetMapping("/chapters/courseId")
    public ResponseEntity<?> getChaptersByCourseId(@RequestParam Long id) {
        return ResponseEntity.ok(chapterService.GetChaptersByCourseId(id));
    }

    @GetMapping("/chapter")
    public ResponseEntity<?> getAllChapters() {
        return ResponseEntity.ok(chapterService.GetAllChapters());
    }
}
