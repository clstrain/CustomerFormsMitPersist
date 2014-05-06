package com.aperea.clscustomerforms;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Requestor.class)
public abstract class Requestor_ {

	public static volatile SingularAttribute<Requestor, Long> id;
	public static volatile SingularAttribute<Requestor, String> lastName;
	public static volatile SingularAttribute<Requestor, String> email;
	public static volatile SingularAttribute<Requestor, Company> company;
	public static volatile SingularAttribute<Requestor, Registration> registration;
	public static volatile SingularAttribute<Requestor, String> firstName;

}

