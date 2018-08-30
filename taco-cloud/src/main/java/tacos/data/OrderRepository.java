package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}

/* estendendo l'interfaccia CrudRepository vengono implementati automaticamente 6 metodi di interrogazione, aggiornamento, cancellazione.
 * inoltre, viene fornita la possbilità di usare DERIVED QUERY semplicemente scrivendo dei metodi con una signature che rispetti le
 * parole chiave sotto.
 */

/* SE VOGLIO SCRIVERE MANUALMENTE QUERY:
 * 	
	//interrogazione con query esplicita
	@Query("order o where o.deliverycity='seattle'")
	list<order> readordersdeliveredinseattle();
 */

/* PAROLE CHIAVE SPRING DATA DERIVED QUERY METHODS*/
/*
List<Order> findByDeliveryZip(String deliveryZip);
List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);*/
/*
IsAfter , After , IsGreaterThan , GreaterThan
IsGreaterThanEqual , GreaterThanEqual
IsBefore , Before , IsLessThan , LessThan
IsLessThanEqual , LessThanEqual
IsBetween , Between
IsNull , Null
IsNotNull , NotNull
IsIn , In
IsNotIn , NotIn
IsStartingWith , StartingWith , StartsWith
IsEndingWith , EndingWith , EndsWith
IsContaining , Containing , Contains
IsLike , Like
IsNotLike , NotLike
IsTrue , True
IsFalse , False
Is , Equals
IsNot , Not
IgnoringCase , IgnoresCase
*/