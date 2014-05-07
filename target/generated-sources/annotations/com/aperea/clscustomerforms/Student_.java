package com.aperea.clscustomerforms;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> lastName;
	public static volatile SingularAttribute<Student, String> phoneNumber;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile ListAttribute<Student, Registration> registrations;
	public static volatile SingularAttribute<Student, String> firstName;

}

