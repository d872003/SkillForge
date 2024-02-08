package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.CreateCategoryRequestDTO;
import website.skillforge.be.entities.Category;
import website.skillforge.be.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDTO createCourseRequestDTO) {
        Category category = new Category();
        category.setName(createCourseRequestDTO.getName());
        category.setCode(createCourseRequestDTO.getCode());
        category.setDescription(createCourseRequestDTO.getDescription());
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }


    public int deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return 1;
    }

    public Category updateCategory(Long id, CreateCategoryRequestDTO category) {
        Category existingCategory = categoryRepository.findCategoryById(id);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
