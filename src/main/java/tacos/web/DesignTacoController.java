package tacos.web;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm (Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Pszenna Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Kukurydziana Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Mielona wołowina", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Kawałki mięsa kurczaka", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Pomidory pokrojone w kostkę", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Sałata", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Ser - Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Ser - Monterey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Pikantny sos pomidorowy", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Śmietana", Ingredient.Type.SAUCE)
                );

        Type[] types = Ingredient.Type.values();

        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(
       List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
