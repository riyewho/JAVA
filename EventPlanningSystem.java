import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Abstract Class for User Actions
abstract class UserActions {
    abstract void createEvent(Scanner scanner);
    abstract void addVenue(Scanner scanner);
    abstract void registerAttendee(Scanner scanner);
    abstract void assignVenueToEvent(Scanner scanner);
    abstract void viewEventDetails();
}

// Venue Class
class Venue {
    private final String name;
    private final int capacity;
    private final List<String> facilities;

    public Venue(String name, int capacity, List<String> facilities) {
        this.name = name;
        this.capacity = capacity;
        this.facilities = new ArrayList<>(facilities); // Create a mutable list
    }

    public String getName() {
        return name;
    }

    public void displayVenueDetails() {
        System.out.println("Venue: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Facilities: " + facilities);
    }
}

// Attendee Class
abstract class Attendee {
    private final String name;
    private final String email;

    public Attendee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public abstract String getSpecialAccess();
}

class RegularAttendee extends Attendee {
    public RegularAttendee(String name, String email) {
        super(name, email);
    }

    @Override
    public String getSpecialAccess() {
        return "No special access.";
    }
}

class VIPAttendee extends Attendee {
    public VIPAttendee(String name, String email) {
        super(name, email);
    }

    @Override
    public String getSpecialAccess() {
        return "Access to VIP Lounge.";
    }
}

class GuestSpeaker extends Attendee {
    public GuestSpeaker(String name, String email) {
        super(name, email);
    }

    @Override
    public String getSpecialAccess() {
        return "Access to Backstage and Media Room.";
    }
}

// Event Class
class Event {
    private final String name;
    private final String date;
    private final String time;
    private Venue venue;
    private final List<Attendee> attendees;

    public Event(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.attendees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void displayEventDetails() {
        System.out.println("\n\nEvent: " + name);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        if (venue != null) {
            venue.displayVenueDetails();
        } else {
            System.out.println("Venue: Not assigned yet.");
        }
        System.out.println("Attendees:");
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - " + attendee.getSpecialAccess());
        }
    }
}

// Implementation of UserActions
class EventPlanning extends UserActions { 
    private final List<Event> events; // Made final
    private final List<Venue> venues; // Made final

    public EventPlanning() {
        events = new ArrayList<>();
        venues = new ArrayList<>();
    }

    @Override
    public void createEvent(Scanner scanner) {
        try {
            System.out.print("Enter Event Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Event Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter Event Time (HH:MM): ");
            String time = scanner.nextLine();
            events.add(new Event(name, date, time));
            System.out.println("Event created successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear the buffer
        }
    }


    @Override
    public void addVenue(Scanner scanner) {
        try {
            System.out.print("Enter Venue Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Venue Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Facilities (comma-separated): ");
            String[] facilitiesArray = scanner.nextLine().split(",");
            List<String> facilities = new ArrayList<>(Arrays.asList(facilitiesArray)); // Make mutable
            venues.add(new Venue(name, capacity, facilities));
            System.out.println("Venue added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for capacity.");
            scanner.nextLine(); // Clear the buffer
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    @Override
    public void registerAttendee(Scanner scanner) {
        if (events.isEmpty()) {
            System.out.println("No events available. Create an event first.");
            return;
        }

        try {
            System.out.println("Select Event:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i).getName());
            }
            int eventChoice = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (eventChoice < 0 || eventChoice >= events.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.print("Enter Attendee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Attendee Email: ");
            String email = scanner.nextLine();
            System.out.println("Select Attendee Type:");
            System.out.println("1. Regular Attendee");
            System.out.println("2. VIP Attendee");
            System.out.println("3. Guest Speaker");
            int typeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Attendee attendee;
            attendee = switch (typeChoice) {
                case 1 -> new RegularAttendee(name, email);
                case 2 -> new VIPAttendee(name, email);
                case 3 -> new GuestSpeaker(name, email);
                default -> {
                    System.out.println("Invalid choice.");
                    yield null; // Use `yield` to return a value from a block in a switch expression
                }
            };

            events.get(eventChoice).addAttendee(attendee);
            System.out.println("Attendee registered successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for the attendee type.");
            scanner.nextLine(); // Clear the buffer
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    @Override
    public void assignVenueToEvent(Scanner scanner) {
        if (events.isEmpty()) {
            System.out.println("No events available. Create an event first.");
            return;
        }
        if (venues.isEmpty()) {
            System.out.println("No venues available. Add a venue first.");
            return;
        }

        try {
            System.out.println("Select Event:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i).getName());
            }
            int eventChoice = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (eventChoice < 0 || eventChoice >= events.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.println("Select Venue:");
            for (int i = 0; i < venues.size(); i++) {
                System.out.println((i + 1) + ". " + venues.get(i).getName());
            }
            int venueChoice = scanner.nextInt() - 1;

            if (venueChoice < 0 || venueChoice >= venues.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            events.get(eventChoice).setVenue(venues.get(venueChoice));
            System.out.println("Venue assigned successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for your choice.");
            scanner.nextLine(); // Clear the buffer
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    @Override
    public void viewEventDetails() {
    if (events.isEmpty()) {
        System.out.println("No events available.");
        return;
    }

    for (Event event : events) {
        event.displayEventDetails();
        System.out.println("---------------------------");
    }
}
    public class EventPlanningSystem {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            EventPlanning system = new EventPlanning();
            
        while (true) {
            System.out.println("\nEvent Planning System");
            System.out.println("1. Create Event");
            System.out.println("2. Add Venue");
            System.out.println("3. Register Attendee");
            System.out.println("4. Assign Venue to Event");
            System.out.println("5. View Event Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1 -> system.createEvent(scanner); // Use the instance to call the method
                case 2 -> system.addVenue(scanner); // Use the instance to call the method
                case 3 -> system.registerAttendee(scanner); // Use the instance to call the method
                case 4 -> system.assignVenueToEvent(scanner); // Use the instance to call the method
                case 5 -> system.viewEventDetails(); // Use the instance to call the method
                case 6 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
}