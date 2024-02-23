package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.createDTO.CreateCategoryRequestDTO;
import website.skillforge.be.entities.Category;
import website.skillforge.be.repository.CategoryRepository;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDTO createCourseRequestDTO) {
        Category category = new Category();
        Date date = new Date();
        category.setName(createCourseRequestDTO.getName());
        category.setCode(createCourseRequestDTO.getCode());
        category.setDescription(createCourseRequestDTO.getDescription());
        category.setCreatedDate(date);
        category.setLastUpdatedDate(date);
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }


    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, CreateCategoryRequestDTO category) {
        Category existingCategory = categoryRepository.findCategoryById(id);
        if (existingCategory != null) {
            Date date = new Date();
            existingCategory.setName(category.getName());
            existingCategory.setCode(category.getCode());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setLastUpdatedDate(date);
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
