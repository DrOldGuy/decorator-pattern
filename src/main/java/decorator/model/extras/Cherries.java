package decorator.model.extras;

import decorator.model.ConeIngredient;
import decorator.model.Extra;

/**
 * This class represents cherry toppings. It implements the
 * {@link Extra} interface, which marks this class as an extra
 * ingredient.
 * 
 * @author Promineo
 *
 */
public class Cherries implements Extra, ConeIngredient {

  private ConeIngredient parent;

  /**
   * The constructor takes a cone ingredient parent object.
   * 
   * @param parent
   */
  public Cherries(ConeIngredient parent) {
    this.parent = parent;
  }

  /**
   * The {@link #serve()} method is declared by the
   * {@link ConeIngredient} interface. It calls the parent serve
   * method first, then prints a line to the console.
   */
  @Override
  public void serve() {
    parent.serve();
    System.out.println("I've added a pile of cherries.");
  }

}
