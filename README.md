# **EventFinder Application**

EventFinder is a dynamic Android application that enables users to discover, explore, and manage various events efficiently. With Firebase integration for seamless data storage and authentication, EventFinder ensures a secure, modern, and user-friendly experience. The app features a sleek UI with Material Design principles, ensuring adaptability to both light and dark themes.

---

## **Team Members**
- **Arda Küçük** (TeamLeader,Developer)
- **Ömer Furkan Akbağ** (Developer)
- **Alp Küçük** (Developer)

---

## **Features**

### **1. User Authentication**
- **Firebase Authentication**:
  - Users can securely register and log in with their email and password.
  - Firebase ensures reliable account management.

### **2. Event Management**
- **Event Listing**:
  - Fetches events dynamically from **Firebase Firestore**.
  - Displays events in a scrollable RecyclerView with a card-based layout for clarity.
- **Event Details**:
  - View comprehensive details, including:
    - Event name, location, date, time, and description.
    - A countdown timer showcasing time remaining until the event begins.
- **Sort & Filter**:
  - Filter and sort events based on criteria such as:
    - Name
    - Location
    - Date

### **3. Firebase Integration**
- **Firebase Firestore**:
  - Stores event data in a structured and scalable format.
- **Firebase Authentication**:
  - Ensures secure and seamless login and registration functionality.

### **4. Modern UI Design**
- Built with **Material Design Components**, providing:
  - Intuitive navigation with Android Navigation Components.
  - Light and dark themes for optimal user experience.

### **5. Dynamic Countdown Timer**
- Displays a real-time countdown for each event, enhancing user engagement.

### **6. Efficient Data Handling**
- **Data Binding**:
  - Provides reactive updates to the UI when data changes.
- **ViewModel**:
  - Manages app data lifecycle-aware, ensuring smooth data handling even during configuration changes.
- **LiveData**:
  - Automatically updates the UI upon data modifications.

---

## **Technical Overview**

### **Key Components**
1. **Navigation**:
   - Single-activity architecture with multiple fragments managed by the Navigation Component.
   - Fragments include:
     - LoginFragment
     - RegisterFragment
     - MainFragment
     - EventListFragment
     - EventDetailFragment
     - FilterFragment

2. **RecyclerView with ListAdapter**:
   - Displays events efficiently with diffing for optimized updates.

3. **Firebase Integration**:
   - Firestore for event data.
   - Authentication for user management.

4. **MVVM Architecture**:
   - Ensures maintainable and testable code.

5. **Material Design**:
   - Provides a visually appealing and consistent user experience.

---

## **Setup Instructions**

### **Prerequisites**
- **Android Studio** (Latest version recommended)
- **Firebase Project**:
  - Set up a Firebase project.
  - Add an Android app to the project.
  - Download the `google-services.json` file and place it in the `app/` directory.

### **Cloning the Repository**
1. Clone the repository:
   ```bash
   git clone https://github.com/AlpKucuk1/EventFinder2.git
Open the project in Android Studio.
Configuring Firebase
Enable Firebase Authentication (Email/Password).
Set up Firestore and configure the events collection schema:
Fields: id, name, location, date, time, description.
Usage
1. Login/Registration
New Users:
Register with a valid email and password.
Existing Users:
Log in to access the event list.
2. Viewing Events
Main Screen:
Browse events in a card-based list.
Cards display essential details (name, date, and location).
Event Details:
Tap a card to view detailed event information, including the countdown timer.
3. Sorting and Filtering
Use the sort/filter menu to:
Sort events by name, date, or location.
Filter events based on preferences.

