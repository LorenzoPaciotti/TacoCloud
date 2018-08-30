package tacos.data;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.Order;
import tacos.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

//	@Query(value="select from order o where ")
//	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}

/* PAROLE CHIAVE SPRING DATA DERIVED QUERY METHODS */
/*
 * List<Order> findByDeliveryZip(String deliveryZip); List<Order>
 * readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate,
 * Date endDate);
 */
/*
 * IsAfter , After , IsGreaterThan , GreaterThan IsGreaterThanEqual ,
 * GreaterThanEqual IsBefore , Before , IsLessThan , LessThan IsLessThanEqual ,
 * LessThanEqual IsBetween , Between IsNull , Null IsNotNull , NotNull IsIn , In
 * IsNotIn , NotIn IsStartingWith , StartingWith , StartsWith IsEndingWith ,
 * EndingWith , EndsWith IsContaining , Containing , Contains IsLike , Like
 * IsNotLike , NotLike IsTrue , True IsFalse , False Is , Equals IsNot , Not
 * IgnoringCase , IgnoresCase
 */