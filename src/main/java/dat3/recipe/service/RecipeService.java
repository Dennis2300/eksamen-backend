package dat3.recipe.service;

import dat3.recipe.dto.RecipeDto;
import dat3.recipe.entity.Category;
import dat3.recipe.entity.Recipe;
import dat3.recipe.repository.CategoryRepository;
import dat3.recipe.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  private RecipeRepository recipeRepository;
  private CategoryRepository categoryRepository;

  public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
    this.recipeRepository = recipeRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<RecipeDto> getAllRecipes(String category) {
    List<Recipe> recipes = category == null ? recipeRepository.findAll() : recipeRepository.findByCategoryName(category);
    List<RecipeDto> recipeResponses = recipes.stream().map((r) -> new RecipeDto(r,false)).toList();
    return recipeResponses;
  }

  public RecipeDto getRecipeById(int idInt) {
    Recipe recipe = recipeRepository.findById(idInt).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    return new RecipeDto(recipe,false);
  }

  public RecipeDto addRecipe(RecipeDto request) {
    if (request.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new recipe");
    }
    Category category = categoryRepository.findByName(request.getCategory()).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Only existing categories are allowed"));
    Recipe newRecipe = new Recipe();
    updateRecipe(newRecipe, request, category);
    recipeRepository.save(newRecipe);
    return new RecipeDto(newRecipe,false);
  }

  public RecipeDto editRecipe(RecipeDto request, int id) {
    checkRoles(request.getOwner());
    if (request.getId() != id) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the id of an existing recipe");
    }
    Category category = categoryRepository.findByName(request.getCategory()).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Only existing categories are allowed"));

    Recipe recipeToEdit = recipeRepository.findById(id).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    updateRecipe(recipeToEdit,request, category);
    recipeRepository.save(recipeToEdit);
    return new RecipeDto(recipeToEdit,false);
  }

  private static void checkRoles(String owner) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    List<String> roles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    boolean isAdmin = roles.contains("ADMIN");
    String name = auth.getName();
    if(!isAdmin && !name.equals(owner)){
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You may only edit/delete your own recipes");
    }
  }

  public void deleteRecipe(int id) {
    Recipe recipe = recipeRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No recipe with this id to delete"));
    checkRoles(recipe.getOwner());
    recipeRepository.delete(recipe);
  }


  private void updateRecipe(Recipe original, RecipeDto r, Category category) {
    original.setName(r.getName());
    original.setOwner(r.getOwner());
    original.setInstructions(r.getInstructions());
    original.setIngredients(r.getIngredients());
    original.setThumb(r.getThumb());
    original.setYouTube(r.getYouTube());
    original.setSource(r.getSource());
    original.setCategory(category);
  }



}

