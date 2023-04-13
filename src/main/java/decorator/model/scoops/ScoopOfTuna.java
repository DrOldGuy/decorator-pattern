package decorator.model.scoops;

import decorator.model.ConeIngredient;
import decorator.model.Scoop;

/**
 * This class represents a scoop of tuna ice cream. It implements the
 * {@link Scoop} interface, which marks this class as an ice cream
 * scoop.
 * 
 * @author Promineo
 *
 */
public class ScoopOfTuna implements Scoop, ConeIngredient {

  private ConeIngredient parent;

  /**
   * The constructor takes a cone ingredient parent object.
   * 
   * @param parent
   */
  public ScoopOfTuna(ConeIngredient parent) {
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
    System.out
        .println("I've added a scoop of tuna ice cream (bleah!).");
  }

}
