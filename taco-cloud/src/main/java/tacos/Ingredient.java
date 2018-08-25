package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}



/*
the most unusual thing about the Ingredient class as defined in Listing 2.1 is
that it seems to be missing the usual set of getter and setter methods, not to mention
useful methods like equals() , hashCode() , toString() and others.
In fact, the @Data annotation at the class level is provided
by Lombok and tells Lombok to generate all of those missing methods as well as a
constructor that accepts all final properties as arguments. By using Lombok, we’re
able to keep the code for Ingredient slim and trim.
*/