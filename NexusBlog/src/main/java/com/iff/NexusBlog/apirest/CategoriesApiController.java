package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RestController;

import com.iff.NexusBlog.dto.CategoryDTO;
import com.iff.NexusBlog.models.Category;
import com.iff.NexusBlog.response.MessageResponse;
import com.iff.NexusBlog.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/categories")
public class CategoriesApiController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<CategoryDTO>>> getCategories() {
    List<Category> categories = categoryService.getAll();

    List<EntityModel<CategoryDTO>> categoryModels = categories.stream()
        .map(category -> {
            CategoryDTO dto = new CategoryDTO(category);
            return EntityModel.of(dto,
                linkTo(methodOn(CategoriesApiController.class).getCategoryById(category.getId())).withSelfRel()
            );
        })
        .toList();

    CollectionModel<EntityModel<CategoryDTO>> collectionModel = CollectionModel.of(categoryModels);
    collectionModel.add(linkTo(methodOn(CategoriesApiController.class).getCategories()).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<CategoryDTO>> getCategoryById(@PathVariable Long id) {
    Category category = categoryService.getById(id); // ou conforme seu service

    CategoryDTO dto = new CategoryDTO(category);

    EntityModel<CategoryDTO> categoryModel = EntityModel.of(dto,
        linkTo(methodOn(CategoriesApiController.class).getCategoryById(id)).withSelfRel(),
        linkTo(methodOn(CategoriesApiController.class).getCategories()).withRel("all-categories") // ou valor default
    );

    return ResponseEntity.ok(categoryModel);
  }

  @PostMapping
  public ResponseEntity<EntityModel<CategoryDTO>> createCategory(@RequestBody Category category) {
    Category createdCategory = categoryService.create(category);
    CategoryDTO dto = new CategoryDTO(createdCategory);

    EntityModel<CategoryDTO> categoryModel = EntityModel.of(dto,
        linkTo(methodOn(CategoriesApiController.class).getCategoryById(createdCategory.getId())).withSelfRel(),
        linkTo(methodOn(CategoriesApiController.class).getCategories()).withRel("all-categories")
    );

    return ResponseEntity
        .created(linkTo(methodOn(CategoriesApiController.class).getCategoryById(createdCategory.getId())).toUri())
        .body(categoryModel);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<EntityModel<CategoryDTO>> updateCategory(@PathVariable Long id, @RequestBody Category categoryData) {
    Category updatedCategory = categoryService.update(id, categoryData);
    CategoryDTO dto = new CategoryDTO(updatedCategory);

    EntityModel<CategoryDTO> categoryModel = EntityModel.of(dto,
        linkTo(methodOn(CategoriesApiController.class).getCategoryById(updatedCategory.getId())).withSelfRel(),
        linkTo(methodOn(CategoriesApiController.class).getCategories()).withRel("all-categories")
    );

    return ResponseEntity.ok(categoryModel);
  }
  
  

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityModel<MessageResponse>> destroyCategory(@PathVariable Long id){
    categoryService.delete(id);

    MessageResponse message = new MessageResponse("Category Successfully deleted");


    EntityModel<MessageResponse> response = EntityModel.of(message,
        linkTo(methodOn(CategoriesApiController.class).getCategories()).withRel("all-categories")
    );

    return ResponseEntity.ok(response);
  }
  
}
