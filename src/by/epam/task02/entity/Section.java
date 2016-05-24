package by.epam.task02.entity;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private String name;
    private List<Dish> dishes;

    public Section() {
        dishes = new ArrayList<>();
    }

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        this.dishes.add(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (name != null ? !name.equals(section.name) : section.name != null) return false;
        return !(dishes != null ? !dishes.equals(section.dishes) : section.dishes != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
    	return getClass().getName() + "@" + "name : " + name + ", dishes : " + dishes;
    }
}
