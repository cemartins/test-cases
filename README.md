Demonstrates a problem while using nested transactions
------------------------------------------------------

Hibernate throws an exception "illegally attempted to associate a proxy with two open Sessions"


To run the test execute:

```

	mvn integration-test
	
```

I am using hibernate and spring-aop to handle transactions so that there is always an open transaction on the server side.

I want to create a nested transaction to work on it in isolation but I get an error. See example below:

Entity e2 created with data from a persisted entity e1 and saved in a nested transaction. E1 has a deep graph not completely initialized.

```

	---
      |
      V
      begin transaction 1
         |
         ---> Read persisted entity e1
                        |
                        |
                        V 
                        
                         begin transaction 2
                         
                         create new transient entity e2
                         copy properties from e1 to e2
                         save e2
                         
                         commit transaction 2
                         
                        |
                        |
         ---------------
         |
         V
      commit transaction 1

```

Maven project with a detailed test case also attached.

What would be the correct way of creating e2 without throwing an exception?

