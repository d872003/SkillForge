package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.CreateCategoryRequestDTO;
import website.skillforge.be.dto.updateDTO.UpdateCategoryDTO;
import website.skillforge.be.entities.Category;
import website.skillforge.be.services.CategoryService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = categoryService.createCategory(createCategoryRequestDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('TEACHER')")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        Category newCategory = categoryService.updateCategory(id, updateCategoryDTO);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name) {
        Category category = categoryService.findCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
