package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CatorgoryListDTO;
import guru.springfamework.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static guru.springfamework.config.SpringFoxConfig.CATEGORY_TAG;

/**
 * Created by jt on 9/26/17.
 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
@Tag(name = CATEGORY_TAG, description = "Категории товаров")
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "списки категорий", description = "Получение списка категорий", tags = {CATEGORY_TAG})
    public CatorgoryListDTO getallCatetories() {
        return new CatorgoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение категории", description = "Получение категории по ИД категории", tags = {CATEGORY_TAG})
    public CategoryDTO getCategoryByName(@PathVariable @Parameter(description = "ИД категории", required = true)
                                         String name)
    {
        return categoryService.getCategoryByName(name);
    }
}
