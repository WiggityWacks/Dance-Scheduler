# My Personal Project: 
# Studio Scheduler

## by Joaquin Garcia

**Q&A**
- *What will the application do?*
  - This application will allow a studio to schedule their classes in each of their rooms.
- *Who will use it?*
  - At a glance, students, parents, and coaches will be able to see their upcoming schedule and where their practice will take place.
- *Why is this project of interest to you?*
  - As a competitive dancer and a coach, scheduling needs to be flexible, easy to find, and easy to set up. Currently, it's hard to find a schedule text in the midst of tens of messages, so separating scheduling from other activities will streamline the communication process.
 
## User Stories
- As a user, I want to be able to add a new dance class to an existing schedule.
- As a user, I want to be able to remove a dance class from the schedule.
- As a user, I want to be able to register a student for a particular class.
- As a user, I want to be able to see the full studio schedule.
- As a user, I want to be able to see the studio schedule for a single day.
- As a user, I want to have the option to save my studio schedule to file.
- As a user, I want to have the option to reload a saved schedule and resume exactly where I left off.

# Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by pressing "Add Class" and following the prompts.
- You can generate the second required action related to adding Xs to a Y by pressing "Remove Class" and using the index number to select an existing class.
- You can see a list of all the classes in a table by pressing "View Classes" then pressing "Weekly".
- You can locate my visual component by pressing "Save" or "Load".
- You can save the state of my application by pressing "Save".
- You can reload the state of my application by pressing "Load".

# Phase 4: Task 2
Sample of events that occur when Studio Scheduler runs (must quit app from console):
- Thu Aug 10 00:05:44 PDT 2023
- Dance class Tech Class on friday has been created.
- Thu Aug 10 00:05:44 PDT 2023
- Tech Class's dance type has been set to Hip Hop.
- Thu Aug 10 00:05:44 PDT 2023
- Tech Class has been added to the schedule.
- Thu Aug 10 00:05:44 PDT 2023
- Joaquin has been added to Tech Class.
- Thu Aug 10 00:05:57 PDT 2023
- Dance class Advanced on Wednesday has been created.
- Thu Aug 10 00:05:57 PDT 2023
- Advanced's dance type has been set to Hip Hop.
- Thu Aug 10 00:05:57 PDT 2023
- Advanced has been added to the schedule.
- Thu Aug 10 00:06:06 PDT 2023
- Joaquin Garcia has been added to Advanced.
- Thu Aug 10 00:06:13 PDT 2023
- Advanced has been successfully removed.

# Phase 4: Task 3
If I had more time to work on the project, I might want to refactor the Dancer, DanceClass, and DanceClassList 
classes and abstract them into super classes (for ex. Student, Class, and ClassSchedule). Most of the methods and purposes for these classes
are applicable to many other types of classes other than just dance, and I could see the application possibly being used for
school classes, extracurriculars, sports, etc. I would also try to implement some of the design patterns and styles we learned
later in the course by making each class more cohesive and coupled, for example in the SchedulerApp class, as it is currently
handling both the console UI and GUI. This has made the class longer, more confusing, and tougher to change without changing certain
aspects of the code. Instead, I could extract methods related to the GUI into its own class and then initialize it, or even make
the Scheduler app a super class with separate console UI and GUI classes. Either way, it will improve both coupling and cohesion.