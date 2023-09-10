package com.example.tacocloud.tacos.web;

import com.example.tacocloud.tacos.Ingredient;
import com.example.tacocloud.tacos.Ingredient.Type;
import com.example.tacocloud.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for(Type type:types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }
//        {wrap=[Ingredient(id=FLTO, name=Flour Tortilla, type=WRAP), Ingredient(id=COTO, name=Corn Tortilla, type=WRAP)], protein=[Ingredient(id=GRBF, name=Ground Beef, type=PROTEIN), Ingredient(id=CARN, name=Carnitas, type=PROTEIN)]
    }
    @GetMapping
    public String showDesignFrom(Model model){
        model.addAttribute("taco",new Taco());
//        List<Ingredient> ingredients = new ArrayList<>(Arrays.asList("Flour Tortilla","Corn Tortilla", "Ground Beef","Carnitas","Diced Tomatoes","Lettuce","Cheddar","Monterrey Jack","Salsa","Sour Cream"));
        return "design";
    }
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        if(errors.hasErrors()){
            System.out.println(errors.toString());
//            return  "redirect:/design";
            return "design";
        }

        log.info("processing design " + taco);
        return "redirect:/orders/current";
    }


    /**
     * java新用法，按照不同TYPE组合
     * @param ingredients
     * @param type
     * @return
     */
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
