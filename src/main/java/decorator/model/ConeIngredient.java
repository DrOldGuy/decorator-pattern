package decorator.model;

/**
 * This interface is the foundation for making the decorator design
 * pattern work. All decorations must implement this interface and all
 * decorations except the base decoration must have a constructor that
 * takes a {@link ConeIngredient} parent. This is used to create a
 * chain like this:
 * 
 * <pre>
 * customer -> 
 *    cone -> 
 *    scoop of ice cream -> 
 *    ingredient 1 -> 
 *    ingredient 2 ->
 *    ...
 * </pre>
 * 
 * @author Promineo
 *
 */
public interface ConeIngredient {
  /**
   * The serve method works from the end of the cone stack
   * (ingredient) to the start (customer). To implement the decorator
   * design pattern, the parent {@link #serve()} method is called,
   * then the current object's serve method is executed.
   */
  void serve();
}
