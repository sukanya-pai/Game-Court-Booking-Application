# Intuit Craft Demo

# Use Cases to be covered:
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
  - This is a debatable requirement. Depends on the system requirements whether the application charges booking amount or not.
  - For now I am assuming that there is no such booking fee needed and the user can just go to the location and pay the cost of court usage on the spot.
-  Users who are not logged in should be asked to login/ register to the application in case they want to book a court. 
  - This is again a debatable environment. 
  - We can either ask the non-subscribed members to login to proceed booking.
  - Or they can still book a system by giving their complete details and email address.
- User should be able to see the court booking histories.

### Nice to have
- User should be able to cancel booking at any time and the court should go back into the pool of available courts
- User should recieve e-mail notifications upon booking court

# Non Functional Requirements:
- System should be available to everyone at all times.
- System should be consistent and show accurate results
- System should handle heavy request flows since many users can login to the system at same time
- System should be secure.
