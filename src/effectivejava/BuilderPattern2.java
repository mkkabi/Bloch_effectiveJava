package effectivejava;

// Builder pattern for class hierarchies
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


/*build method in each subclass’s builder is declared to return the correct subclass: 
the build method of NyPizza.Builder returns NyPizza, while the one in Calzone.
Builder returns Calzone. This technique, wherein a subclass method is declared to return 
a subtype of the return type declared in the super-class, is known as covariant return typing. 
It allows clients to use these builders without the need for casting. 
The client code for these “hierarchical builders” is essentially identical to the code for 
the simple NutritionFacts builder. The example client code shown next assumes static imports 
on enum constants for brevity:*/

abstract class Pizza {

	public enum Topping {

		HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
	}
	final Set<Topping> toppings;

	abstract static class Builder<T extends Builder<T>> {

		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}

		abstract Pizza build();

		// Subclasses must override this method to return "this"
		protected abstract T self();
	}

	Pizza(Builder<?> builder) {
		toppings = builder.toppings.clone(); // See Item 50
	}
}

class NyPizza extends Pizza {

	public enum Size {

		SMALL, MEDIUM, LARGE
	}
	private final Size size;

	public static class Builder extends Pizza.Builder<Builder> {

		private final Size size;

		public Builder(Size size) {
			this.size = Objects.requireNonNull(size);
		}

		@Override
		public NyPizza build() {
			return new NyPizza(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}

	private NyPizza(Builder builder) {
		super(builder);
		size = builder.size;
	}
}

class Calzone extends Pizza {

	private final boolean sauceInside;

	public static class Builder extends Pizza.Builder<Builder> {

		private boolean sauceInside = false; // Default

		public Builder sauceInside() {
			sauceInside = true;
			return this;
		}

		@Override
		public Calzone build() {
			return new Calzone(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}

	private Calzone(Builder builder) {
		super(builder);
		sauceInside = builder.sauceInside;
	}
}

public class BuilderPattern2{
	public static void main(String[] args) {
		NyPizza pp = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.HAM).
				addTopping(Pizza.Topping.SAUSAGE).build();
		Calzone calzone = new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();
	}
}