package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name="Taco_Order")
@Slf4j
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date placedAt;

	@NotBlank(message = "Name is required")
	@Column(name="DELIVERYNAME")
	private String name;
	@NotBlank(message = "Street is required")
	@Column(name="DELIVERYSTREET")
	private String street;
	@NotBlank(message = "City is required")
	@Column(name="DELIVERYCITY")
	private String city;
	@NotBlank(message = "State is required")
	@Column(name="DELIVERYSTATE")
	private String state;
	@NotBlank(message = "Zip code is required")
	@Column(name="DELIVERYZIP")
	private String zip;
	//@CreditCardNumber(message = "Not a valid credit card number")
	@Column(name="cc_number")
	private String ccNumber;
	//@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	@Column(name="cc_expiration")
	private String ccExpiration;
	//@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	@Column(name="cc_cvv")
	private String ccCVV;

	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>();

	public void addDesign(Taco design) {
		this.tacos.add(design);
		log.debug(this.tacos.toString());
	}

	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}

}