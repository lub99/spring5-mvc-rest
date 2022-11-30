package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CategoryListDto;
import guru.springfamework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @GetMapping( "/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
