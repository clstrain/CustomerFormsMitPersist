package com.aperea.clscustomerforms;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registration.class)
public abstract class Registration_ {

	public static volatile SingularAttribute<Registration, String> nonRevNumber;
	public static volatile ListAttribute<Registration, Student> students;
	public static volatile SingularAttribute<Registration, String> cienaRelation;
	public static volatile SingularAttribute<Registration, Boolean> isTrainingCredits;
	public static volatile SingularAttribute<Registration, Boolean> isAgree;
	public static volatile SingularAttribute<Registration, Date> courseDate;
	public static volatile SingularAttribute<Registration, Address> siteAddress;
	public static volatile SingularAttribute<Registration, Boolean> hasProjector;
	public static volatile SingularAttribute<Registration, String> pocPhoneNumber;
	public static volatile SingularAttribute<Registration, Requestor> requestor;
	public static volatile SingularAttribute<Registration, String> pocLastName;
	public static volatile SingularAttribute<Registration, Long> id;
	public static volatile SingularAttribute<Registration, Date> registrationDate;
	public static volatile SingularAttribute<Registration, String> purchaseOrderNumber;
	public static volatile SingularAttribute<Registration, String> pocEmail;
	public static volatile SingularAttribute<Registration, Boolean> hasWhiteBoards;
	public static volatile SingularAttribute<Registration, String> courseType;
	public static volatile SingularAttribute<Registration, String> additionalNotes;
	public static volatile SingularAttribute<Registration, Boolean> hasLaptops;
	public static volatile SingularAttribute<Registration, Address> shippingAddress;
	public static volatile SingularAttribute<Registration, Boolean> isTrainingSite;
	public static volatile SingularAttribute<Registration, String> courseNumber;
	public static volatile SingularAttribute<Registration, String> pocFirstName;
	public static volatile SingularAttribute<Registration, String> courseName;

}

