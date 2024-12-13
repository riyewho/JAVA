## Event Planning System
This console-based application helps users manage events, attendees, and venues efficiently. It leverages Object-Oriented Programming (OOP) principles for a structured and robust design.
Key Features
Event Management: Create, manage, and view event details (name, date, time, venue).
Venue Management: Add venues with name, capacity, and facilities.
Attendee Management: Register attendees with different types (Regular, VIP, Guest Speaker) and access levels.
Venue Assignment: Assign venues to specific events.
Data Storage: Uses ArrayLists for in-memory storage (database required for persistence).
Error Handling: Includes basic error handling; more robust handling needed for production.
OOP Implementation
The system utilizes core OOP principles:
Abstraction: The UserActions abstract class defines a contract for user actions, implemented concretely by EventPlanning. The abstract Attendee class allows for different attendee types.
Encapsulation: Classes (Venue, Attendee, Event) encapsulate data and methods, protecting internal state through private attributes and controlled access.
Inheritance: RegularAttendee, VIPAttendee, and GuestSpeaker inherit from Attendee, promoting code reuse.
Polymorphism: The Event class's attendees list handles different Attendee subclasses uniformly.
SDG Integration: SDG 9 - Industry, Innovation, and Infrastructure
This project aligns with SDG 9 by:
Innovation: The system can be expanded to include features like automated scheduling and resource optimization.
Resilient Infrastructure: The design can be enhanced for scalability and reliability, including data backup and disaster recovery.
Sustainable Industrialization: Integration with sustainable venue booking platforms can promote environmentally friendly practices.
Instructions
1. Save the Code: Save the provided Java code as EventPlanningSystem.java. Ensure the file is saved in a location you can easily access.
2. Compile the Code: Open a terminal and navigate to the file's directory.Then, compile the code using a Java compiler.
3. Execution: After successful compilation, run the program using VS Code or any code editor that has Java extension.
4. User Interaction: The console will present a menu of options:
Create Event
Add Venue
Register Attendee
Assign Venue to Event
View Event Details
Exit
Follow the on-screen prompts to interact with the system. Input data according to the specified formats to avoid errors.
