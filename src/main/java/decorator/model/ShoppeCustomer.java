package decorator.model;

import lombok.Value;

/**
 * The customer is the base object of the cone stack. As such, it does
 * not have a constructor that takes a parent object. But, as with the
 * other classes in the stack, it must extend {@link ConeIngredient}.
 * 
 * This class uses Lombok annotations. To see the results of the
 * annotations you must have Lombok installed into your IDE. To
 * install Lombok into Eclipse, follow the instructions here:
 * https://projectlombok.org/setup/eclipse.
 * 
 * Class-level annotations:
 * 
 * @Value This Lombok annotation creates getters for all instance
 *        variables, and toString, equals and hashCode methods. It
 *        also creates a constructor that takes the customer name as a
 *        parameter.
 * 
 * @author Promineo
 *
 */
@Value
public class ShoppeCustomer implements ConeIngredient {
  private String customerName;

  /**
   * Since {@link ShoppeCustomer} is the base object in the cone
   * stack, the serve method doesn't need to do anything.
   */
  @Override
  public void serve() {}
}
