
// Telescoping constructor pattern - does not scale well!

public class NutritionFacts {

  private final int servingSize;    // (mL) required
  private final int servings;       // (per container) required
  private final int calories;       // optional
  private final int fat;            // (g) optional
  private final int sodium;         // (mg) optional
  private final int carbohydrate;   // (g) optional

  public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories) {
    this(servingSize, servings, calories, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat) {
    this(servingSize, servings, calories, fat, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
    this(servingSize, servings, calories, fat, sodium, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
    this.servingSize = servingSize;
    this.servings = servings;
    this.calories = calories;
    this.fat = fat;
    this.sodium = sodium;
    this.carbohydrate = carbohydrate;
  }
}

NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);

// NOTE The telescoping constructor pattern works, but it is hard to write client code when there
// are many parameters, and harder still to read it.

// -------------------------------------------------------------------------------------------------

// JavaBeans Pattern - allows inconsistency, mandates mutability

public class NutritionFacts {

  // Parameters initialized to default values (if any)
  private int servingSize = -1; // Required; no default value
  private int servings = -1; // Required; no default value
  private int calories = 0;
  private int fat = 0;
  private int sodium = 0;
  private int carbohydrate = 0;

  public NutritionFacts() { }

  // Setters
  public void setServingSize(int val) { servingSize = val; }
  public void setServings(int val) { servings = val; }
  public void setCalories(int val) { calories = val; }
  public void setFat(int val) { fat = val; }
  public void setSodium(int val) { sodium = val; }
  public void setCarbohydrate(int val) { carbohydrate = val; }
}

NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);
cocaCola.setCarbohydrate(27);

// NOTE JavaBean may be in an inconsistent state partway through its construction.

// NOTE JavaBeans pattern precludes the possibility of making a class immutable.

// -------------------------------------------------------------------------------------------------

// Builder Pattern

public class NutritionFacts {

  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public static class Builder {

    // Required parameters
    private final int servingSize;
    private final int servings;

    // Optional parameters - initialized to default values
    private int calories = 0;
    private int fat = 0;
    private int carbohydrate = 0;
    private int sodium = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
}

NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
  .calories(100)
  .sodium(35)
  .carbohydrate(27)
  .build();

// NOTE The Builder pattern simulates named optional parameters.

// A builder for objects of type T
public interface Builder<T> {
  public T build();
}

Tree buildTree(Builder<? extends Node> nodeBuilder) { ... }

// NOTE Class.newInstance breaks compile-time exception checking.

// NOTE The Builder pattern is a good choice when designing classes whose constructors or static
// factories would have more than a handful of parameters.
