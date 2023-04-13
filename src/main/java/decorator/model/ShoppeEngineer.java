/**
 * 
 */
package decorator.model;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import decorator.exception.IceCreamException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * The shoppe engineer takes the order, prepares the cone, then serves
 * it to the customer. Note that because the order details are held as
 * instance variables, the class is not thread safe. Therefore only
 * one order can be taken at a time (which is kind of true in real
 * life as well).
 * 
 * This class uses Lombok annotations. To see the results of the
 * annotations you must have Lombok installed into your IDE. To
 * install Lombok into Eclipse, follow the instructions here:
 * https://projectlombok.org/setup/eclipse.
 * 
 * Class-level annotations:
 * 
 * @ToString This is a Lombok annotation that creates a usable
 *           toString method.
 * 
 * @EqualsAndHashCode This Lombok annotation creates the equals() and
 *                    hashCode() methods using the instance variables.
 * 
 * @RequiredArgsConstructor This Lombok annotation creates a
 *                          constructor with all instance variables
 *                          that have the @NonNull annotation.
 * 
 * @author Promineo
 *
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ShoppeEngineer {
  /** These are the ingredient weights used in sorting. */
  private static final int EXTRA_WEIGHT = 3;
  private static final int SCOOP_WEIGHT = 2;
  private static final int CONE_WEIGHT = 1;

  /** @Getter creates a getName() method. */
  @Getter
  /** @NonNull adds name to the constructor. */
  @NonNull
  private String name;

  /*
   * The customer, ingredients, and cone are internal variables and
   * are not exposed to any other classes (i.e., getters are not
   * created for them).
   */
  private ShoppeCustomer customer;
  private List<Class<? extends ConeIngredient>> ingredients;
  private ConeIngredient cone;

  /**
   * This method is used to take and save the order details.
   * 
   * @param customer The order customer.
   * @param ingredients The order ingredients, given as a list of
   *        classes. The ingredients are added in the order they are
   *        given, so it's probably best to start with a cone.
   */
  public void takeOrder(ShoppeCustomer customer,
      List<Class<? extends ConeIngredient>> ingredients) {

    this.customer = customer;
    this.ingredients = ingredients;
  }

  /**
   * This method is called to prepare (or build) the cone and add
   * ingredients. Ingredients are sorted in cone -> ice cream ->
   * extras order.
   */
  public void prepareCone() {
    System.out.printf("Hi %s! I've got this marvelous ice cream cone "
        + "for you!%n", customer.getCustomerName());

    cone = buildCone();
  }

  /**
   * This method exercises the builder design pattern by calling the
   * {@link ConeIngredient#serve()} method on the final element of the
   * ingredient stack. Each ingredient calls the parent serve()
   * method, then executes its own method code. The method code prints
   * a status line.
   */
  public void serveCone() {
    cone.serve();
    System.out.printf("So, here ya go!%n%n");
  }

  /**
   * This method creates a cone ingredient stack. Each element of the
   * stack except for the base (customer) takes the parent element in
   * the constructor.
   * 
   * @return The last element of the cone. This is essentially a
   *         single-linked list anchored by the end element, which is
   *         returned by this method.
   */
  private ConeIngredient buildCone() {
    List<Class<? extends ConeIngredient>> ingredientClasses =
        sortClassesByType();

    return buildConeStack(ingredientClasses);
  }

  /**
   * This method uses Java Reflection to build the cone stack. See
   * comments in the method that explain what is happening.
   * 
   * @param ingredientClasses The sorted list of ingredients.
   * @return The last element of the stack.
   */
  private ConeIngredient buildConeStack(
      List<Class<? extends ConeIngredient>> ingredientClasses) {

    /*
     * Set the base object as the customer. This is the only object
     * that extends ConeIngredient that does not have a parent.
     */
    ConeIngredient last = customer;

    /*
     * Iterate through the list of ingredient classes. Each class
     * object is used as the parent for the next object. This yields a
     * single-linked list as follows: customer -> cone -> ingredients.
     */
    for(Class<? extends ConeIngredient> ingredientClass : ingredientClasses) {
      try {
        /*
         * Use Java Reflection to obtain a constructor object. In the
         * builder design pattern, each element must have a
         * constructor that takes the parent object. This builds a
         * single-linked list of elements.
         */
        Constructor<? extends ConeIngredient> constructor =
            ingredientClass.getConstructor(ConeIngredient.class);

        /*
         * Create a new ingredient object and pass the parent object.
         */
        last = constructor.newInstance(last);
      }
      catch (Exception e) {
        throw new IceCreamException(e);
      }
    }

    return last;
  }

  /**
   * Sort the ingredient list. This method moves the cone to the top
   * of the list, followed by ice cream scoops, followed by the extra
   * ingredients (like sprinkles). This prevents scenarios like having
   * the ice cream on the bottom with the cone on top.
   * 
   * @return The sorted list.
   */
  private List<Class<? extends ConeIngredient>> sortClassesByType() {
    List<Class<? extends ConeIngredient>> ingredientClasses =
        new LinkedList<>(ingredients);

    ingredientClasses.sort((c1, c2) -> {
      List<Class<?>> interfaces1 = Arrays.asList(c1.getInterfaces());
      List<Class<?>> interfaces2 = Arrays.asList(c2.getInterfaces());

      int weight1 = interfaces1.contains(Cone.class) ? CONE_WEIGHT
          : interfaces1.contains(Scoop.class) ? SCOOP_WEIGHT
              : EXTRA_WEIGHT;

      int weight2 = interfaces2.contains(Cone.class) ? CONE_WEIGHT
          : interfaces2.contains(Scoop.class) ? SCOOP_WEIGHT
              : EXTRA_WEIGHT;

      return weight1 - weight2;
    });

    return ingredientClasses;
  }

}
