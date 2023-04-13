# Decorator Pattern

This project demonstrates how to implement the Decorator Design Pattern by building an ice cream cone. The decorator pattern follows this approach:

* Each decoration (cone ingredient) has a constructor that takes a parent decoration. The exception is the base decoration, which acts as an anchor to the decoration stack. The base decoration (the person ordering the cone) does not have a constructor that takes a parent decoration because there is no parent of the base decoration.

* The interface (ConeIngredient) defines a single method ( _serve()_ ). Each decoration calls the  _serve()_  method on the parent before implementing its own  _serve()_  method. Each  _serve()_  method simply prints a message to the console. In another implementation you would expect the decorators to do something more significant.

In this implementation, the decorations are sorted. This is not a requirement for the Decorator Design Pattern but is required in this implementation so that the ice cream isn't served with the scoops on the bottom and the cone on the top. Decorations are classed as cone, scoop or extra. The sorted decoration stack looks like this:

```
  Customer -> Cone -> Ice Cream -> Extras
```

The Customer is the base decoration. As such, it does not have a constructor that takes a ConeIngredient object; it has no parent. But like all the cone ingredients, the Customer implements the ConeIngredient interface.

# Investigate the project

Start with the class decorator.iceCreamShoppe. This class has a main method and is the entry point to the Java application. Use this class as the base to investigate the other classes. The decorators are found in the following packages:

* decorator.cones
* decorator.extras
* decorator.scoops

# Sorting the decorators

Decorators are sorted prior to building the cone stack. To achieve this, marker interfaces are used to apply sorting weights. All cone classes must implement the interface Cone. The ice cream classes implement the interface Scoop. And extras, like sprinkles, implement the interface Extra. The marker interfaces are in the decorator.model package.