package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String NAME = "name";
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    public void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS =  categoryService.getAllCategories();

        //then
        assertNotNull(categoryDTOS);
        assertEquals(2, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {
        //given
        Category category = new Category();
        category.setName(NAME);

        when(categoryRepository.findCategoryByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO =  categoryService.getCategoryByName(NAME);

        //then
        assertNotNull(categoryDTO);
        assertEquals(NAME, categoryDTO.getName());
    }
}