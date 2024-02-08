package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.CreateChapterRequestDTO;
import website.skillforge.be.services.ChapterService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class ChapterController {
    @Autowired
    private ChapterService chapterService;


    @PostMapping("/chapter")
    public ResponseEntity createChapter(@RequestBody CreateChapterRequestDTO createChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.CreateChapter(createChapterRequestDTO));
    }

    @DeleteMapping("/chapter/{id}")
    public ResponseEntity deleteChapter(@PathVariable Long id) {
        chapterService.DeleteChapter(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity getChapterById(@PathVariable Long id) {
        return ResponseEntity.ok(chapterService.GetChapter(id));
    }

    @GetMapping("/chapter")
    public ResponseEntity getAllChapters() {
        return ResponseEntity.ok(chapterService.GetAllChapters());
    }
}
