slow-crud-app
=============

A 'typical' Web CRUD Applcation with Spring and Hibernate and lots of configurable bad performance characteristics.

The application domain is a job's board for job seekers that have dismissed from their job - see onlyhadonejob.com for details.

The application's performance characteristics will be controllable via a JMX API which will allow you to switch 
on/off particular performance characteristics and adjust values where appropriate.

A goal is to have this as a Java 6 source compatible app as a majority of the enterprise market runs this
A goal is to ensure that the Spring/Hibernate app can start in a consistent, stable, known good state.
A goal is to have consistent, repeatable performance problems for a given environment (at this stage, std AWS instances)
