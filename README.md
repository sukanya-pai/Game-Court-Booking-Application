# Game Court Booking Application

# High Level Design:

- The request comes from browser or app of different client devices like laptop, phone or tablets etc. 
- The request is routed to correct application backend server at the backend by means of a load balancer (to not burden any servers), the app server serves the requests and if necessary contacts the database/storage unit and returns the response to the client. 
- To decrease the latency of the requests, we can store some of the results on app server cache. 
- By making use of load balancer and multiple server and database copies, we ensure that the whole system does not go down. There is no single point of failure. 
![alt HLD Flow](https://github.com/SSPai123/Intuit_Craft_Demo/blob/main/HLD.png?raw=true)
- When lots of concurrent requests occur for booking, for providing best possible user experience, the implementation should be in such a way that whoever clicks proceeds to pay the payment amount first, we lock the court for that user for specified period of time until the user pays successfully.
  - By doing this we avoid the issue of multiple users competing for the same court.
  - Only the eventual latest courts would be shown to other users on the platform (Slight inconsistency in the status of court during the time of payment is acceptable.)

# Low Level Design:
## Use Cases to be covered:
## Admin
- Admin should be able to add or remove multiple game options like Tennis, Badminton, Table tennis, Football etc.
- Admin should be able to add or remove multiple locations
- Admin should be able to add or remove details of courts and slots available in different parts of the city.
- Here care should be taken while removing courts or location or game options considering that there would be users who have booked the courts. The user should be notified about this removal whenever the admin plans to remove this.

## User
### Must have
- User should be able to login/ register to the system.
- Users who are not logged in should be able to atleast browse through the list of various courts available.
- User should be able to browse through list of details (name, price) various slots and courts present in different parts of the city for different game options.
- Users should be able to see list of time slots available for a given court at a particular location.
- User should be able to book court for any game of their choice along with an option to select the time slot.
- When the user books a court, the selected court and time slot details of a location are sent and booked in the name of a user. 
- User can be redirected to payment portal to pay the booking amount or the user can just book the slot and pay the amount in person. 
-  Users who are not logged in should be asked to login/ register to the application in case they want to book a court. 
   - This is again a debatable environment. 
   - We can either ask the non-subscribed members to login to proceed booking.
   - Or they can still book a system by giving their complete details and email address.
- User should be able to see the court booking histories.
![alt use case diagram](https://github.com/SSPai123/Intuit_Craft_Demo/blob/main/Game%20Court%20Booking%20System%20-%20Use%20Case%20Diagram.png)

### Nice to have
- User can be able to search/filter court details based on location, time slot and game options.
- User should be able to cancel booking at any time and the court should go back into the pool of available courts
- User should recieve e-mail notifications upon booking court

# Non Functional Requirements:
- System should be available to everyone at all times.
- System should be consistent and show accurate results
- System should handle heavy request flows since many users can login to the system at same time
- System should be secure.
