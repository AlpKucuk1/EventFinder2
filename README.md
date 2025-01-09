# **EventFinder Application**

EventFinder is a dynamic Android application that enables users to discover, explore, and manage various events efficiently. With Firebase integration for seamless data storage and authentication, EventFinder ensures a secure, modern, and user-friendly experience. The app features a sleek UI with Material Design principles, ensuring adaptability to both light and dark themes.

---

## **Project Title**
**EventFinder**  
An event discovery and management platform for users to explore upcoming events, sort/filter them based on preferences, and view detailed information.

---

## **Project Description**
EventFinder is designed to provide an effortless experience for users to manage event information in one place. With real-time Firebase integration for data storage and authentication, the app offers features like sorting, filtering, and countdown timers for events. It adopts a modern single-activity architecture with the Android Navigation Component to ensure seamless navigation across fragments.

---

## **Features**

### **1. User Authentication**
- Secure registration and login functionality using Firebase Authentication.

### **2. Event Management**
- Fetch and display events from Firebase Firestore.
- Card-based RecyclerView for event listing.
- Detailed event view with a countdown timer.

### **3. Sorting and Filtering**
- Sort events by name, location, or date.
- Filter events dynamically for ease of discovery.

### **4. Modern UI**
- Built with Material Design components for an elegant and responsive user experience.
- Light and dark theme support.

### **5. Animations**
- Welcome page animations for an engaging onboarding experience.

---

## **Setup Instructions**

### **Prerequisites**
1. Install **Android Studio** (Latest version recommended).
2. Create a **Firebase Project**:
   - Add an Android app in Firebase.
   - Download the `google-services.json` file and place it in the `app/` directory.

### **Steps to Set Up the Project**

#### **Prerequisites**
1. Install **Android Studio** (Latest version recommended).
2. Create a **Firebase Project**:
   - Add an Android app to Firebase.
   - Download the `google-services.json` file and place it in the `app/` directory.

#### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/AlpKucuk1/EventFinder2.git
Open the project in Android Studio.
Sync the project with Gradle files:
If prompted, allow Android Studio to download necessary dependencies.
Configure Firebase:
Enable Firebase Authentication with email/password in the Firebase console.
Set up Firestore with a collection named events and the following fields:
id (integer)
name (string)
location (string)
date (string)
time (string)
description (string)
Build and run the application:
Connect an Android device or use an emulator.
Ensure debugging options are enabled if using a physical device.
Test the application:
Register a new user or log in with an existing account.
Explore the event list, view event details, and try sorting/filtering options.
Usage Instructions
Login and Registration
New Users:
Register with a valid email and password.
Existing Users:
Log in to access the main event list.
Event Management
View all events on the main screen in a card-based format.
Tap an event card to view detailed information, including a countdown timer.
Sorting and Filtering
Use the sort/filter menu to:
Sort events by name, location, or date.
Filter events dynamically based on your preferences.
Contributions
Alp Küçük
Created the foundation for the code and the main alpha version of EventFinder.
Developed:
Login and Register pages.
BindingAdapters for efficient UI updates.
ViewModel for lifecycle-aware data handling.
Firebase API integration for Firestore and Authentication.
Arda Küçük
Focused on enhancing the user experience with animations and UI elements.
Developed:
Welcome page and its animations.
"Remember Me" button functionality.
MainFragment logic and design.
XML layouts for:
FragmentEventDetail
FragmentWelcome
Ömer Furkan Akbağ
Played a key role in app navigation and design consistency.
Developed:
NavGraph setup for seamless navigation.
EventListFragment logic and integration.
Designed:
Login/Register page layouts.
FragmentRegister XML.
FragmentWelcome XML.
FragmentEventDetail XML.
