package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Heroes implements Hero {

    private  String name;
    private  int strength;
    private  int agility;
    private  int intelligence;
    private  int hitPoints;
    private  int damage;

    private  HeroInventory heroInventory;

    protected Heroes(String name, int strength, int agility, int intelligence, int hitPoints, int damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.heroInventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.heroInventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.heroInventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.heroInventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.heroInventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {
        try {
            Field field = this.heroInventory.getClass().getDeclaredField("commonItems");
            field.setAccessible(true);
            return ((Map<String, Item>) field.get(this.heroInventory)).values();

        } catch (Exception ignored) {
        }
        return null;
    }


    @Override
    public void addItem(Item item) {

        this.heroInventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {

        this.heroInventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {

        StringBuilder out = new StringBuilder();

        out.append(String.format(". %s: %s", getClass().getSimpleName(), getName()))
                .append(System.lineSeparator())
                .append(String.format("###HitPoints: %d", getHitPoints()))
                .append(System.lineSeparator())
                .append(String.format("###Damage: %d", getDamage()))
                .append(System.lineSeparator())
                .append(String.format("###Strength: %d", getStrength()))
                .append(System.lineSeparator())
                .append(String.format("###Agility: %d", getAgility()))
                .append(System.lineSeparator())
                .append(String.format("###Intelligence: %d", getIntelligence()))
                .append(System.lineSeparator())
                .append("###Items: ");
        if (this.getItems().isEmpty()) {
            out.append("None");
        } else {
            out.append(String.format("%s", this.getItems().stream().map(Item::getName)
                    .map(String::valueOf).collect(Collectors.joining(", "))));

        }
        return out.toString().trim();
    }
}
