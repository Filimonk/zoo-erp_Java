package bank.service.api;

import java.util.*;

import bank.domain.model.Category;
import bank.domain.OperationType;

public interface CategoryService {
    Category createCategory(OperationType type, String name);
    void deleteCategory(String id);
    Category getCategoryById(String id);
    List<Category> getAllCategories();
}

