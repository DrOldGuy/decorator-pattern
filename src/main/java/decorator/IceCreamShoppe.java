package decorator;

import java.util.List;
import decorator.model.ConeIngredient;
import decorator.model.ShoppeEngineer;
import decorator.model.ShoppeCustomer;
import decorator.model.cones.SugarCone;
import decorator.model.cones.WaffleCone;
import decorator.model.extras.CandySprinkles;
import decorator.model.extras.Cherries;
import decorator.model.extras.MandMs;
import decorator.model.scoops.ScoopOfChocolate;
import decorator.model.scoops.ScoopOfTuna;

/**
 * This is the entry point to the Java application.
 * 
 * @author Promineo
 *
 */
public class IceCreamShoppe {

  /* These are the two cone builders. */
  ShoppeEngineer julie = new ShoppeEngineer("Julie");
  ShoppeEngineer ralph = new ShoppeEngineer("Ralph");

  /**
   * The main method simply creates an object of type
   * {@link IceCreamShoppe} and then calls the instance method
   * {@link #openForBusiness()}. This lets the instance method access
   * the two employee instance variables.
   * 
   * @param args
   */
  public static void main(String[] args) {
    new IceCreamShoppe().openForBusiness();
  }

  /**
   * This method takes a couple of orders from customers, builds and
   * serves the cones. Note that the ingredients can be added in any
   * order (just like a real ice cream shoppe). So, you might say, "I
   * would like a scoop of chocolate on a sugar cone with candy
   * sprinkles on top." The shop employee would change the order of
   * ingredients by putting the cone on the bottom, the ice cream next
   * followed by the sprinkies. In the same way, the ingredients are
   * sorted so that the cone comes first, followed by the ice cream
   * scoops, followed by any extra ingredients.
   */
  private void openForBusiness() {
    // @formatter:off
    takeOrder(julie, new ShoppeCustomer("Sam"),
        List.of(
            Cherries.class, 
            ScoopOfChocolate.class,
            WaffleCone.class, 
            CandySprinkles.class)
        );
    
    takeOrder(ralph, new ShoppeCustomer("Martha"),
        List.of(
            ScoopOfChocolate.class,
            ScoopOfTuna.class,
            SugarCone.class,
            MandMs.class
        )
    );
    // @formatter:on
  }

  /**
   * This method takes an engineer, a customer, and a list of
   * ingredient classes to build an ice cream cone. The actual cone
   * building is done by {@link ShoppeEngineer#prepareCone()}.
   * 
   * @param shoppeEngineer The ice cream shoppe engineer (employee).
   * @param shoppeCustomer The customer making the order.
   * @param ingredients The list of ingredient classes. These classes
   *        utilize the decorator design pattern to build a cone of
   *        any available ingredients.
   */
  private void takeOrder(ShoppeEngineer shoppeEngineer,
      ShoppeCustomer shoppeCustomer,
      List<Class<? extends ConeIngredient>> ingredients) {

    shoppeEngineer.takeOrder(shoppeCustomer, ingredients);
    shoppeEngineer.prepareCone();
    shoppeEngineer.serveCone();
  }

}
