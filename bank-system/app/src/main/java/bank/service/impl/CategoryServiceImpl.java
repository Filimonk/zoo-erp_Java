package bank.service.impl;

import java.util.*;

import bank.domain.model.Category;
import bank.domain.factory.CategoryFactory;
import bank.service.api.CategoryService;
import bank.domain.OperationType;

public class CategoryServiceImpl implements CategoryService {
    private final Map<String, Category> categories = new HashMap<>();
    private final CategoryFactory factory;

    public CategoryServiceImpl(CategoryFactory factory) {
        this.factory = factory;
    }

    @Override
    public Category createCategory(OperationType type, String name) {
        Category category = factory.create(type, name);
        categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteCategory(String id) {
        if (!categories.containsKey(id)) {
            throw new NoSuchElementException("Категория с таким id не найдена");
        }
        categories.remove(id);
    }

    @Override
    public Category getCategoryById(String id) {
        Category category = categories.get(id);
        if (category == null) {
            throw new NoSuchElementException("Категория с таким id не найдена");
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}

