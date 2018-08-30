package tacos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
	@Id
	private final String id;
	private final String name;
	
	@Enumerated(EnumType.STRING)//impone che il campo db sia un tipo testuale (nel caso di un'enumerazione)
	private final Type Type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}

/*
In order to declare this as a JPA entity, Ingredient must be annotated with @Entity .
And its id property must be annotated with @Id to designate it as the property that will
uniquely identify the entity in the database.
In addition to the JPA-specific annotations, you’ll also note that I’ve added
a @NoArgsConstructor annotation at the class level. JPA requires that entities have a
no arguments constructor, so Lombok’s @NoArgsConstructor does that for us. I don’t
want to be able to use it, though, so I’ve made it private by setting
the access attribute to AccessLevel.PRIVATE . And since there are final properties that
must be set, I’ve also set the force attribute to true (which will result in the Lombok-
generated) constructor setting them to null ).
I also had to a @RequiredArgsConstructor . The @Data implicitly adds a required
arguments constructor, but when @NoArgsConstructor is used, that constructor gets
removed. An explicit @RequiredArgsConstructor ensures that we will still have a
required arguments constructor in addition to the private no arguments constructor.
*/