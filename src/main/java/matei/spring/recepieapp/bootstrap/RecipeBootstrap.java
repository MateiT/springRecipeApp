package matei.spring.recepieapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import matei.spring.recepieapp.domain.*;
import matei.spring.recepieapp.repositories.CategoryRepository;
import matei.spring.recepieapp.repositories.RecipeRepository;
import matei.spring.recepieapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private  final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipies());
        log.debug("Loading bootstram data!");
    }

    private List<Recipe> getRecipies(){

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> tableUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> teaUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(eachUomOptional.isEmpty() || tableUomOptional.isEmpty() || teaUomOptional.isEmpty()
                || dashUomOptional.isEmpty() || pintUomOptional.isEmpty() || cupUomOptional.isEmpty()){
            throw new RuntimeException("Expected UOM not found!");
        }

        UnitOfMeasure each = eachUomOptional.get();
        UnitOfMeasure tablespoon = tableUomOptional.get();
        UnitOfMeasure teaspoon = teaUomOptional.get();
        UnitOfMeasure dash = dashUomOptional.get();
        UnitOfMeasure pint = pintUomOptional.get();
        UnitOfMeasure cup = cupUomOptional.get();

        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");

        if(americanOptional.isEmpty() || mexicanOptional.isEmpty()){
            throw new RuntimeException("Expected Category not found!");
        }

        Category american = americanOptional.get();
        Category mexican = mexicanOptional.get();

        Recipe guacamoleRecipie = new Recipe();
        guacamoleRecipie.setDescription("Perfect Guacamole");
        guacamoleRecipie.setPrepTime(10);
        guacamoleRecipie.setCookTime(5);
        guacamoleRecipie.setDifficulty(Difficulty.EASY);
        guacamoleRecipie.setDirections(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quis mauris eget orci fringilla mollis eget quis nunc. Sed varius quam at sem malesuada venenatis sit amet ac nisl. Fusce augue nunc, fermentum in molestie ac, dignissim vitae enim. In hac habitasse platea dictumst. Phasellus commodo est turpis. Vivamus sit amet blandit lacus. Proin viverra semper elit, vel consectetur neque consectetur sodales.\n" +
                "\n" +
                "Ut a urna ex. Mauris lacinia est ligula. Nullam risus velit, placerat vitae tristique sit amet, dapibus sit amet arcu. Sed eu lobortis ipsum, vel egestas purus. Integer ornare nisl nisi, ac venenatis ligula rutrum ut. Phasellus consectetur turpis arcu, vel commodo elit porta et. Maecenas tellus libero, dapibus vitae libero lobortis, accumsan sodales urna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tortor quam, dapibus sit amet turpis id, semper finibus urna. Curabitur ornare, tellus semper varius lacinia, augue nulla euismod dui, sed semper odio risus eget sem. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ");
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecepieNotes("Proin viverra varius lectus, non porttitor nisi vehicula fringilla. Donec in nisi dolor. Integer et tellus ut lectus bibendum imperdiet ac eu magna. Nullam eu lectus quis tortor mattis hendrerit. Suspendisse suscipit pharetra lectus, porttitor faucibus dui aliquet in. Etiam egestas arcu augue, a interdum est tempor non. In commodo urna at lorem tempus, ut fermentum dui pretium. Praesent nisi sem, iaculis at eleifend sed, maximus in est. Donec ut tellus tincidunt, efficitur arcu non, accumsan tortor. Vestibulum sit amet sapien elit. Duis ultricies dolor nec luctus faucibus. Ut elementum nec urna non pulvinar. Suspendisse ac nibh vitae sapien accumsan egestas eu a quam. Donec tincidunt in ante et iaculis. ");
        guacamoleRecipie.setNotes(guacamoleNotes);

        guacamoleRecipie.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), each));
        guacamoleRecipie.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoon));
        guacamoleRecipie.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoon));
        guacamoleRecipie.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoon));
        guacamoleRecipie.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), each));
        guacamoleRecipie.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoon));
        guacamoleRecipie.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dash));
        guacamoleRecipie.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), each));

        guacamoleRecipie.getCategories().add(american);
        guacamoleRecipie.getCategories().add(mexican);

        //add to return list
        recipes.add(guacamoleRecipie);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecepieNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), each));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), each));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cup));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), each));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), each));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), each));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), each));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), each));

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
