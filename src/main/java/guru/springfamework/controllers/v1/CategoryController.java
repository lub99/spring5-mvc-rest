package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CategoryListDto;
import guru.springfamework.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<CategoryListDto> getCategories() {
        return ResponseEntity.ok(
                new CategoryListDto(categoryService.getAllCategories())
        );
    }

    @GetMapping( "/{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(
                categoryService.getCategoryByName(name)
        );
    }
}
