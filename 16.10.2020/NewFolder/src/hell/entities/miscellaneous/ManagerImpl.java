package hell.entities.miscellaneous;


import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.*;

import java.util.*;

public class ManagerImpl implements Manager {

    private static int count;
    private static Hero hero;

    private final Map<String, Hero> heroRepository;

    public ManagerImpl() {

        this.heroRepository = new HashMap<>();
    }


    @Override
    public String addHero(List<String> arguments) {

        String name = arguments.get(0);
        String type = arguments.get(1);

        switch (type) {

            case "Assassin":
                hero = new Assassin(name);
                break;
            case "Barbarian":
                hero = new Barbarian(name);
                break;
            case "Wizard":
                hero = new Wizard(name);
                break;
        }

        heroRepository.put(name, hero);

        return String.format("Created %s - %s", type, name);
    }

    @Override
    public String addItem(List<String> arguments) {

        String itemName = arguments.get(0);
        String heroName = arguments.get(1);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));

        Item item = new CommonItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);

        heroRepository.get(heroName).addItem(item);

        return String.format("Added item - %s to Hero - %s", itemName, heroName);
    }

    @Override
    public String addRecipe(List<String> arguments) {

        String recipeName = arguments.get(0);
        String heroName = arguments.get(1);
        int strengthBonus = Integer.parseInt(arguments.get(2));
        int agilityBonus = Integer.parseInt(arguments.get(3));
        int intelligenceBonus = Integer.parseInt(arguments.get(4));
        int hitPointsBonus = Integer.parseInt(arguments.get(5));
        int damageBonus = Integer.parseInt(arguments.get(6));

        List<String> requiredItems = new ArrayList<>();
        for (int i = 7; i < arguments.size(); i++) {
            requiredItems.add(arguments.get(i));
        }

        Recipe recipe = new RecipeItem(recipeName,
                strengthBonus,
                agilityBonus,
                intelligenceBonus,
                hitPointsBonus,
                damageBonus,
                requiredItems);

        heroRepository.get(heroName).addRecipe(recipe);

        return String.format("Added recipe - %s to Hero - %s", recipeName, heroName);
    }

    @Override
    public String inspect(List<String> arguments) {

        StringBuilder out = new StringBuilder();
        String heroName = arguments.get(0);
        hero = heroRepository.get(heroName);

        out.append(String.format("Hero: %s, Class: %s", heroName, hero.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", hero.getHitPoints(), hero.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("Strength: %d", hero.getStrength()))
                .append(System.lineSeparator())
                .append(String.format("Agility: %d", hero.getAgility()))
                .append(System.lineSeparator())
                .append(String.format("Intelligence: %d", hero.getIntelligence()))
                .append(System.lineSeparator());

        if (hero.getItems().isEmpty()) {

            out.append("Items: None");

        } else {

            hero.getItems().forEach(e -> out.append("Items:")
                    .append(System.lineSeparator())
                    .append(String.format("###Item: %s", e.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("###+%d Strength", e.getStrengthBonus()))
                    .append(System.lineSeparator())
                    .append(String.format("###+%d Agility", e.getAgilityBonus()))
                    .append(System.lineSeparator())
                    .append(String.format("###+%d Intelligence", e.getIntelligenceBonus()))
                    .append(System.lineSeparator())
                    .append(String.format("###+%d HitPoints", e.getHitPointsBonus()))
                    .append(System.lineSeparator())
                    .append(String.format("###+%d Damage", e.getDamageBonus()))
                    .append(System.lineSeparator()));
        }

        return out.toString().trim();
    }

    @Override
    public String quit() {

        StringBuilder out = new StringBuilder();

        heroRepository.values().stream().sorted((a, b) -> {

            int first = (int) (a.getIntelligence() + a.getAgility() + a.getStrength());
            int second = (int) (b.getIntelligence() + b.getAgility() + b.getStrength());

            int sort = Integer.compare(second, first);

            if (sort == 0) {

                first = (int) (a.getDamage() + a.getHitPoints());
                second = (int) (b.getDamage() + b.getHitPoints());
                sort = Integer.compare(second, first);
            }
            return sort;
        })
                .forEach(e -> out.append(++count)
                        .append(e.toString()).append(System.lineSeparator()));

        return out.toString().trim();
    }
}
