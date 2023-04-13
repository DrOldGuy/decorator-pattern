/**
 * 
 */
package decorator.exception;

/**
 * This exception is thrown when an ice cream cone ingredient cannot
 * be created.
 * 
 * @author Promineo
 *
 */
@SuppressWarnings("serial")
public class IceCreamException extends RuntimeException {

  /**
   * @param cause
   */
  public IceCreamException(Throwable cause) {
    super(cause);
  }
}
