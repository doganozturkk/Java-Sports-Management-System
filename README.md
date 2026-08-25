<h1 align="center">
  <span style="
    background: linear-gradient(to right, #f89820, #5382a1);
    -webkit-background-clip: text;
    color: transparent;
    font-size: 40px;
    font-weight: 900;
  ">
    Java Sports Management System
  </span>
</h1>

<p align="center" style="
  font-size: 18px;
  color: #555;
  margin-top: -10px;
">
  <i>A complete sports management system developed progressively through eight Java assignments</i>
</p>

<p align="center">
  <img src="https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExYnRtem15ZWZtNXRjMXUzamd2OHluZjdtaHRwdW9hMnpndTRlc2t5aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/12oTlUqDjqhTUs/giphy.gif" width="320" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Object--Oriented%20Programming-f89820?logo=openjdk&logoColor=white&style=for-the-badge">
  <img src="https://img.shields.io/badge/GUI-Java%20Swing-5382a1?logo=java&logoColor=white&style=for-the-badge">
  <img src="https://img.shields.io/badge/Collections-Generics-6f42c1?style=for-the-badge">
  <img src="https://img.shields.io/badge/Serialization-GZIP-2ea44f?style=for-the-badge">
  <img src="https://img.shields.io/badge/Multithreading-Synchronization-e44d26?style=for-the-badge">
</p>

---

## 📌 Overview

A comprehensive **Java-based Sports Management System** developed progressively across eight assignments.

The project starts with fundamental object-oriented programming concepts and gradually evolves into a complete application featuring **encapsulation, inheritance, abstraction, interfaces, custom exceptions, graphical user interfaces, collections, event handling, object serialization, persistent data storage, multithreading, and synchronization**.

Each assignment represents a new stage of development and builds upon the functionality implemented in the previous stage.

The final version combines a graphical sports management system with a **100-meter running competition powered by Java threads**, allowing multiple athletes to compete concurrently on synchronized running tracks.

---

## 📁 Project Structure

```text
Java-Sports-Management-System/
│
├── Assignment-01/
│   ├── Athlete.java
│   ├── Match.java
│   ├── ScheduledDate.java
│   └── Training.java
│
├── Assignment-02/
│   ├── infrastructure/
│   │   └── Venue.java
│   │
│   └── sport/
│       ├── Athlete.java
│       ├── Match.java
│       ├── ScheduledDate.java
│       └── Training.java
│
├── Assignment-03/
│   ├── infrastructure/
│   │   ├── Hall.java
│   │   ├── Stadium.java
│   │   └── Venue.java
│   │
│   └── sport/
│       ├── Athlete.java
│       ├── Event.java
│       ├── Match.java
│       ├── ScheduledDate.java
│       ├── SportsDiscipline.java
│       └── Training.java
│
├── Assignment-04/
│   └── ...
│
├── Assignment-05/
│   └── ...
│
├── Assignment-06/
│   └── ...
│
├── Assignment-07/
│   └── ...
│
├── Assignment-08/
│   ├── infrastructure/
│   │   ├── Hall.java
│   │   ├── RunningTrack.java
│   │   ├── Stadium.java
│   │   └── Venue.java
│   │
│   ├── sport/
│   │   ├── AddingAthleteException.java
│   │   ├── AddingMatchException.java
│   │   ├── Athlete.java
│   │   ├── Competition.java
│   │   ├── Event.java
│   │   ├── LauncherThread.java
│   │   ├── Match.java
│   │   ├── ScheduledDate.java
│   │   ├── SportsDiscipline.java
│   │   └── Training.java
│   │
│   └── si/
│       └── um/
│           └── opj/
│               └── ozturk/
│                   ├── listeners/
│                   ├── persistence/
│                   └── ui/
│
└── README.md
```

---

## 🧩 Assignment Progression

The project was developed incrementally, with each assignment introducing new Java concepts and extending the existing sports management system.

### Assignment 01 — Basic Classes

The first stage establishes the core domain model.

Implemented classes:

* `Athlete`
* `Match`
* `ScheduledDate`
* `Training`

Main concepts:

* Classes and objects
* Attributes
* Constructors
* Getters and setters
* `LocalDate`
* Basic object-oriented design
* Age-based competition validation

---

### Assignment 02 — Encapsulation & Packages

The second stage expands the system with venue management and introduces proper package organization.

Main additions:

* `Venue`
* Athlete arrays inside matches
* Match management
* Athlete management
* `toString()` implementations
* Parameterized constructors
* Encapsulation
* `infrastructure` and `sport` packages

The project uses **plain arrays** for the required match and athlete collections at this stage.

---

### Assignment 03 — Inheritance & Abstraction

The third stage introduces a more structured domain model.

New components:

* `Venue` as an abstract class
* `Hall`
* `Stadium`
* `Event`
* `SportsDiscipline`

Main concepts:

* Inheritance
* Abstract classes
* Method overriding
* `super()`
* Enumerations
* Polymorphism
* Shared event attributes

`Match` and `Training` are refactored around the common `Event` superclass.

---

### Assignment 04 — Interfaces & Exceptions

The fourth stage introduces interfaces and custom exception handling.

New components:

* `Competition`
* `AddingAthleteException`
* `AddingMatchException`

The system now validates:

* Athlete eligibility
* Athlete assignment to matches
* Match assignment to venues
* Stadium scheduling restrictions
* Invalid athlete/match operations

Main concepts:

* Interfaces
* Custom exceptions
* Exception handling
* Reference-based object relationships
* Runtime validation

---

### Assignment 05 — Graphical User Interface

The fifth stage transforms the system into a graphical application using **Java Swing**.

The GUI provides management functionality for:

* Athletes
* Venues
* Matches
* Events

The interface is organized inside a unified application window with multiple views.

Main concepts:

* Java Swing
* `JFrame`
* `JPanel`
* Forms and controls
* Layout managers
* GUI-based data management

---

### Assignment 06 — Collections & Event Handling

The sixth stage upgrades the application by introducing Java collections and GUI event responses.

The system begins using parameterized collections such as:

```java
ArrayList<T>
```

Event handling is implemented using different listener approaches, including:

* Independent listener classes
* Anonymous inner classes

Main concepts:

* Collections
* Generics
* `ArrayList`
* Event-driven programming
* Action listeners
* Anonymous inner classes
* Independent classes

---

### Assignment 07 — Serialization & Persistent Storage

The seventh stage adds permanent data storage.

Application data can be serialized into a compressed binary file and automatically restored when the application starts.

The persistence layer uses:

* Java object serialization
* `ObjectOutputStream`
* `ObjectInputStream`
* `GZIPOutputStream`
* `GZIPInputStream`

The application stores and reloads:

* Athletes
* Events
* Venues
* Matches

This allows data entered through the GUI to survive application restarts.

---

### Assignment 08 — Multithreading & 100-Meter Competition

The final stage introduces **Java multithreading and synchronization**.

A 100-meter running competition is implemented using multiple concurrent athlete threads.

New components include:

* `RunningTrack`
* `LauncherThread`
* `Runnable` athletes
* Synchronized track allocation
* Concurrent race execution
* Running-time calculation
* Result sorting
* Winner detection

The stadium automatically creates **eight 100-meter running tracks**.

Each eligible athletics athlete runs in an independent Java thread.

The stadium uses a synchronized section when assigning available tracks, ensuring that two athletes cannot acquire the same track simultaneously.

---

## 🏃 100-Meter Running Competition

The final assignment simulates a complete running competition.

### Race Flow

```text
Athletes
    │
    ▼
Filter ATHLETICS athletes
    │
    ▼
Create a Thread for each athlete
    │
    ▼
Start all threads
    │
    ▼
Acquire an available RunningTrack
    │
    ▼
Simulate the race
    │
    ▼
Calculate running time
    │
    ▼
Release the track
    │
    ▼
Sort final results
    │
    ▼
Determine the winner
```

Each athlete implements:

```java
Runnable
```

and therefore provides its own `run()` method.

The running simulation uses `Thread.sleep()` with randomized delays to simulate the time required to complete the race.

The fastest recorded time is maintained as the current best time, and the athlete with that time is identified as the winner.

---

## 🔐 Thread Synchronization

Race tracks are shared resources.

To prevent multiple athlete threads from acquiring the same track simultaneously, the stadium synchronizes access to the track collection.

Conceptually:

```java
synchronized (tracks) {
    // Find an available track
    // Mark it as in use
    // Return the track
}
```

This ensures safe concurrent access to the shared running-track resource.

---

## 🖥️ Graphical Interface

The final application includes a Swing-based management interface.

The main window provides separate sections for:

* 👤 **Athletes**
* 🏟️ **Venues**
* 🏆 **Matches**
* 📅 **Events**

The application uses a tabbed interface to keep different management operations inside the same window.

The GUI also connects the management system with the underlying data model, allowing users to create and manage the entities used by the application.

---

## 💾 Data Persistence

Application data is stored using Java object serialization.

The serialized data is compressed using **GZIP**, reducing the size of the stored binary data.

The persistence layer automatically:

1. Loads existing data when the application starts.
2. Restores athletes, events, venues, and matches.
3. Allows the application to continue working with the restored data.
4. Saves the current state when the application closes.

The main persistence file is:

```text
sports_data.gz
```

---

## 🛠️ Technologies

* **Java**
* **Java Swing**
* **Object-Oriented Programming**
* **Java Collections Framework**
* **Generics**
* **Java Serialization**
* **GZIP Compression**
* **Java Threads**
* **Runnable**
* **Synchronization**
* **Java Time API**
* **Custom Exceptions**
* **Event-Driven Programming**

---

## ✨ Key Features

✔ Object-oriented sports management model

✔ Athlete, match, event, training and venue management

✔ Hall and stadium specialization

✔ Sports discipline enumeration

✔ Athlete eligibility validation

✔ Custom exception handling

✔ Java Swing graphical interface

✔ Collection-based data management

✔ Generic `ArrayList` collections

✔ Independent and anonymous event listeners

✔ Persistent binary data storage

✔ GZIP-compressed serialization

✔ Automatic data loading and saving

✔ Multithreaded 100-meter running competition

✔ Eight synchronized running tracks

✔ Concurrent athlete execution

✔ Automatic race result sorting

✔ Winner and best-time detection

---

## 🚀 How to Run

The project can be opened in a Java-compatible IDE such as **IntelliJ IDEA**.

For the final application, run:

```text
si.um.opj.ozturk.ui.Main
```

The application will:

1. Load previously saved data if available.
2. Start the graphical user interface.
3. Initialize the sports management system.
4. Start the 100-meter competition using Java threads.
5. Display the race results in the console.
6. Save application data when the application is closed.

---

## 📊 Example Race Output

A typical race execution produces output similar to:

```text
🏁 100M RACE COMPETITION 🏁

 Stadium : Stadium Arena
 Tracks  : 8
 Athletes: 8

🏃 Athlete #1 started running on Track #1
🏃 Athlete #2 started running on Track #2
🏃 Athlete #3 started running on Track #3
🏃 Athlete #4 started running on Track #4
...

 🏁 FINAL RESULTS 🏁

1. Athlete #4 → 1048 ms
2. Athlete #2 → 1127 ms
3. Athlete #7 → 1193 ms
...

🏆 WINNER: Athlete #4
🏆 BEST TIME: 1048 ms
```

Because the race simulation uses randomized delays and concurrent execution, the exact results may differ between runs.

---

## 🎯 Learning Objectives

This project demonstrates the practical application of a wide range of Java programming concepts.

### Object-Oriented Programming

* Classes and objects
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Interfaces
* Method overriding

### Java Collections

* `ArrayList`
* Generic collections
* Sorting with `Comparator`

### Exception Handling

* Custom exceptions
* Validation
* Exception propagation

### GUI Programming

* Java Swing
* Panels
* Forms
* Tabs
* Layout managers
* Event listeners

### File Handling

* Object serialization
* Binary file storage
* GZIP compression
* Persistent application state

### Multithreading

* `Thread`
* `Runnable`
* Concurrent execution
* `sleep()`
* Synchronization
* Shared resources
* Race result coordination

---

## 📈 Project Evolution

The project demonstrates how a relatively simple collection of Java classes can gradually evolve into a complete application:

```text
Basic Classes
      ↓
Encapsulation & Packages
      ↓
Inheritance & Abstraction
      ↓
Interfaces & Exceptions
      ↓
Graphical User Interface
      ↓
Collections & Event Handling
      ↓
Serialization & Persistence
      ↓
Multithreading & Synchronization
```

Each stage adds a new layer of functionality while building upon the previous implementation.

---

## 🎓 Academic Context

This project was developed as part of an **Object-Oriented Programming course at the University of Maribor**.

---

## 📄 License

This project is licensed under the MIT License — feel free to fork it, extend it, and experiment with new ideas!

---

## 🙌 Author

Developed by: **[@doganozturkk](https://github.com/doganozturkk)**
