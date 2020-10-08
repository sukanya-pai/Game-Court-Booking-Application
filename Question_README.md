Rohan lives in Bangalore and he likes to play Tennis. There are few tennis courts around the
place where he lives but whenever he goes to any nearby court, he sees that the court is
already occupied and that he will have to book the court in person but Rohan is too busy to go
to the court every time to just book it.

You being a developer has been asked to develop an online application where people can book
slots and courts available in different parts of the city for various game options like Tennis,
Badminton, Table tennis, Football, etc.

With this application, Rohan should be able to book court for any game of his choice and he
must have an option to select the time slot as well. Ensure that your application is scalable in
terms of supporting it for multiple cities, games, courts, etc

Your application should support guest accounts along with subscribed users and it must include
following APIs
- Get all courts
- For a given court, get the time slots
- Book a slot

You may modify the APIs based on your design but make sure that these functionalities are supported
User interface is not expected, you can demonstrate through REST APIs. Your code has to be
modular, scalable and include unit tests.
Example:
  - Bangalore
      - Badminton:
          - Play 365 - 300/-
          - Silver String sports - 280/-
      - Tennis:
          - Play 365 - 450/-
          - PlayMania - 400/-
  - Delhi
      - Badminton:
          - Silver String sports - 250/-
          - Sports central - 300/-
      - Football:
          - Play 365 - 2000/-
          - PlayMania - 2800/-
