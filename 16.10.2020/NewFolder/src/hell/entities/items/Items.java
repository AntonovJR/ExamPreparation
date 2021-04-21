package hell.entities.items;

import hell.interfaces.Item;

public abstract class Items implements Item {
    private String name;
    private int strengthBonus;
    private int agilityBonus;
    private int intelligenceBonus;
    private int hitPointsBonus;
    private int damageBonus;

    protected Items(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        this.setName(name);
        this.setStrengthBonus(strengthBonus);
        this.setAgilityBonus(agilityBonus);
        this.setIntelligenceBonus(intelligenceBonus);
        this.setHitPointsBonus(hitPointsBonus);
        this.setDamageBonus(damageBonus);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setStrengthBonus(int strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    private void setAgilityBonus(int agilityBonus) {
        this.agilityBonus = agilityBonus;
    }

    private void setIntelligenceBonus(int intelligenceBonus) {
        this.intelligenceBonus = intelligenceBonus;
    }

    private void setHitPointsBonus(int hitPointsBonus) {
        this.hitPointsBonus = hitPointsBonus;
    }

    private void setDamageBonus(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthBonus() {
        return this.strengthBonus;
    }

    @Override
    public int getAgilityBonus() {
        return this.agilityBonus;
    }

    @Override
    public int getIntelligenceBonus() {
        return this.intelligenceBonus;
    }

    @Override
    public int getHitPointsBonus() {
        return this.hitPointsBonus;
    }

    @Override
    public int getDamageBonus() {
        return this.damageBonus;
    }
}
