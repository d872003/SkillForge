package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateChapterRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateChapterDTO;
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

    @PutMapping("/chapter/{id}")
    public ResponseEntity updateChapter(@PathVariable Long id, @RequestBody UpdateChapterDTO updateChapterRequestDTO) {
        return ResponseEntity.ok(chapterService.UpdateChapter(id, updateChapterRequestDTO));
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
