package decorator.model.cones;

import decorator.model.Cone;
import decorator.model.ConeIngredient;

/**
 * This class represents a waffle cone. It implements the {@link Cone}
 * interface, which marks this class as a cone.
 * 
 * @author Promineo
 *
 */
public class WaffleCone implements Cone, ConeIngredient {

  private ConeIngredient parent;

  /**
   * The constructor takes a cone ingredient parent object.
   * 
   * @param parent
   */
  public WaffleCone(ConeIngredient parent) {
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
        .println("I've started with a nice, fresh waffle cone.");
  }

}
