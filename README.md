# EventFinder Application

EventFinder is an Android application that allows users to explore and manage various events. The app features Firebase integration for data storage and user authentication, as well as a modern and user-friendly UI with a focus on functionality and design.

---

## **Team Members**
- **Arda Küçük** (Team Lead, Developer)
- **Ömer Furkan Akbağ** (Developer)
- **Alp Küçük** (Developer)

---

## **Features**

### **User Authentication**
- **Firebase Authentication**:
  - Users can register and log in using their email and password.

### **Event Management**
- **Event Listing**:
  - Events are fetched from **Firebase Firestore** and displayed in a scrollable list.
- **Event Details**:
  - Users can view detailed information about individual events, including the event's name, location, date, and description.
  - A countdown feature shows the remaining time until the event starts.
- **Sort & Filter**:
  - Events can be sorted and filtered based on their location.

### **Firebase Integration**
- **Firebase Firestore**:
  - Used to store and retrieve event data.
- **Firebase Authentication**:
  - Secure login and registration functionality.

### **Modern UI Design**
- Built with **Material Design Components** for a sleek and consistent interface.
- Support for both light and dark themes.

### **Additional Features**
- **Dynamic Countdown Timer**:
  - Displays the remaining time until each event starts.
- **Data Binding**:
  - Used for efficient and reactive UI updates.

---

## **Setup Instructions**

### **Prerequisites**
- **Android Studio** (Latest version recommended)
- **Firebase Project**:
  - Create a Firebase project and add an Android app.
  - Download the `google-services.json` file and place it in the `app/` directory.

### **Cloning the Repository**
1. Clone the repository:
   ```bash
   git clone https://github.com/AlpKucuk1/EventFinder2.git
Open the project in Android Studio.
Usage
Login/Registration:
New users can register using their email and password.
Existing users can log in to access the event list.
View Events:
Events are displayed in a card-based list.
Each card includes event details such as date, name, and location.
Event Details:
Tap on an event to view its detailed information and countdown timer.
Sort & Filter:
Use the sort button to filter events by location or other criteria.
Contributors
This project was developed by the following team members:

