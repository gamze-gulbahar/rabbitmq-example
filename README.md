# rabbitmq-example
Producer sents a package to a RabbitMQ queue, including the URL (https://stackoverflow.com/questions/13445589/jsoup-thread-safety).
Consumer connects to the same queue, gets the package and opens it. Then it visits the URL inside the package and obtains the page title, then prints it out.

—————————————————————————

You should store amqp-client-5.7.1.jar, jsoup-1.13.1.jar, slf4j-api-1.7.26.jar, slf4j-simple-1.7.26.jar files in the same directory as the executables.

—————————————————————————

- You can compile the project with the command below: 

$ javac -cp amqp-client-5.7.1.jar:jsoup-1.13.1.jar Producer.java Consumer.java

—————————————————————————

_Hint!_

You may want to export CP before running so that you can use it shortly in the next command, like this: 

$ export CP=.:amqp-client-5.7.1.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar:jsoup-1.13.1.jar 

—————————————————————————


- You can run the Producer with the command below: 

$ java -cp $CP Producer

—————————————————————————

- You can run the Consumer with the command below: 

$ java -cp $CP Consumer
