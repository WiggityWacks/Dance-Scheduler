package ui;


import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SchedulerApp extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private static final String JSON_STORE = "./data/dancestudio.json";
    private DanceClassList classList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs Scheduler application
    public SchedulerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runScheduler();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runScheduler() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                printEventLogToConsole();
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good day!");

    }

    private void printEventLogToConsole() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user's command
    private void processCommand(String command) {
        if (command.equals("v")) {
            doView();
        } else if (command.equals("a")) {
            doAdd();
        } else if (command.equals("r")) {
            doRemove();
        } else if (command.equals("e")) {
            doEnroll();
        } else if (command.equals("s")) {
            saveSchedule();
        } else if (command.equals("l")) {
            loadSchedule();
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHi! What would you like to do? Please type your command:");
        System.out.println("\tv - View Classes");
        System.out.println("\ta - Add Class");
        System.out.println("\tr - Remove Class");
        System.out.println("\te - Enroll Dancer to Class");
        System.out.println("\ts - Save Schedule to File");
        System.out.println("\tl - Load Schedule from File");
        System.out.println("\tq - Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes classList and sets up Dance Schedule GUI
    private void init() {
        classList = new DanceClassList();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Dance Schedule Manager");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printEventLogToConsole();
                System.exit(0);
            }
        });

        setVisible(true);
    }



    // EFFECTS: adds button panel to desktop
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(new JButton(new ViewScheduleAction()));
        buttonPanel.add(new JButton(new AddClassAction()));
        buttonPanel.add(new JButton(new RemoveClassAction()));
        buttonPanel.add(new JButton(new EnrollInClassAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));

        controlPanel.add(buttonPanel, BorderLayout.CENTER);
    }



    // MODIFIES: this
    // EFFECTS: conducts a view class list interaction
    private void doView() {
        String selection = "";
        List<DanceClass> showList = classList.getWeeklySchedule();
        while (!(selection.equals("w") || selection.equals("d"))) {
            System.out.println("\nView:");
            System.out.println("\tw - weekly");
            System.out.println("\td - daily");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("d")) {
            List<DanceClass> dailyList = new ArrayList<>();
            System.out.println("\nWhich day (Sunday-Saturday):");
            String day = input.next();
            day = day.toLowerCase();

            for (DanceClass dc : classList.getWeeklySchedule()) {
                if (day.equals(dc.getClassDate())) {
                    dailyList.add(dc);
                }
            }
            showList = dailyList;
        }
        System.out.println(classList.scheduleToString(showList));
    }

    // MODIFIES: this
    // EFFECTS: conducts an add class interaction
    private void doAdd() {
        System.out.println("\nEnter class details:");
        System.out.println("\nName?");
        String className = input.next();
        System.out.println("\nDay of the week?");
        String date = input.next();
        date = date.toLowerCase();


        DanceClass newDanceClass = new DanceClass(className, date);
        System.out.println("\nStyle of dance?");
        DanceType newDanceType = new DanceType(input.next());
        newDanceClass.setDanceType(newDanceType);
        classList.addClass(newDanceClass);
        System.out.println("\nYou have successfully added '" + className + "' to the schedule.");

    }

    // MODIFIES: this
    // EFFECTS: conducts a remove class interaction
    private void doRemove() {
        int i = 0;
        System.out.println("\nWhich class do you want to remove?");
        for (DanceClass danceClass: classList.getWeeklySchedule()) {
            System.out.println(i++ + " - " + danceClass.getClassName());
        }


        try {
            int index = input.nextInt();
            String removedClass = classList.getClass(index).getClassName();
            classList.removeClass(index);
            System.out.println("\nYou have successfully removed '" + removedClass + "' from the schedule.");
        } catch (InputMismatchException ex) {
            System.out.println("\nInvalid input. Please try again.");
            input.nextLine();
        }
    }

    // REQUIRES: classList has size > 0
    // MODIFIES: this
    // EFFECTS: conducts an enroll student to class interaction
    private void doEnroll() {
        int i = 1;
        System.out.println("\nWhich class do you want to enroll?");
        for (DanceClass danceClass: classList.getWeeklySchedule()) {
            System.out.println(i++ + " - " + danceClass.getClassName());
        }
        try {
            int index = input.nextInt();
            DanceClass selectedClass = classList.getClass(index - 1);
            String className = selectedClass.getClassName();
            System.out.println("\nWho do you want to enroll? Enter name:");
            String name = input.next();
            System.out.println("\nAge:");
            int age = input.nextInt();
            Dancer newDancer = new Dancer(name, age);
            selectedClass.addDancer(newDancer);
            System.out.println("\nYou have successfully enrolled '" + name + "' into " + className + ".");
        } catch (InputMismatchException ex) {
            System.out.println("\nInvalid input. Please try again.");
            input.nextLine();
        }
    }

    // EFFECTS: save schedule to file
    private void saveSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(classList);
            jsonWriter.close();
            System.out.println("Saved schedule to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads class list from file
    private void loadSchedule() {
        try {
            classList = jsonReader.read();
            System.out.println("Loaded schedule to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            SchedulerApp.this.requestFocusInWindow();
        }
    }

    /**
     * Represents action taken when user wants to view their schedule
     */
    private class ViewScheduleAction extends AbstractAction {

        ViewScheduleAction() {
            super("View Schedule");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createWindow();
        }

        // EFFECTS: sets the frame to see the schedule
        private void createWindow() {
            final int WIDTH = 560;
            final int HEIGHT = 200;
            JInternalFrame frame = new JInternalFrame("Weekly or Daily?");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createUI(frame);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
        }

        // EFFECTS: allows the user to choose from weekly or daily schedule view
        private void createUI(final JInternalFrame frame) {
            String[] options = {"Weekly", "Daily"};
            var selection = JOptionPane.showOptionDialog(null,
                    "Choose one",
                    "Schedule",
                    0, 2, null, options, options[0]);

            if (selection == 0) {
                showFullScheduleUI();
            } else if (selection == 1) {
                showDailyScheduleUI();
            }

        }

        // EFFECTS: shows table of a daily class schedule (chosen from sunday - saturday)
        private void showDailyScheduleUI() {
            int i = 0;
            String selectedDay = JOptionPane.showInputDialog(null,
                    "Day?", "Enter day", JOptionPane.QUESTION_MESSAGE);
            List<DanceClass> classes = classList.getDailySchedule(selectedDay);

            int rowSize = classes.size();
            String[] columnNames = {"Name", "Dance Style", "Day of the Week"};
            Object[][] data = new Object[rowSize][3];
            for (DanceClass dc: classes) {
                data[i][0] = dc.getClassName();
                data[i][1] = dc.getDanceType().getName();
                data[i][2] = dc.getClassDate();
                i++;
            }
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            add(scrollPane);

            JInternalFrame frame = new JInternalFrame(selectedDay + " Schedule", true, true, false, false);
            frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            frame.setContentPane(scrollPane);
            frame.pack();
            frame.setVisible(true);
            desktop.add(frame);
        }

        // EFFECTS: prints out the schedule as a table with each row containing a dance class's
        //          name, style, and day of the week.
        private void showFullScheduleUI() {
            int i = 0;
            int rowSize = classList.getWeeklySchedule().size();
            String[] columnNames = {"Name", "Dance Style", "Day of the Week"};
            Object[][] data = new Object[rowSize][3];
            for (DanceClass dc: classList.getWeeklySchedule()) {
                data[i][0] = dc.getClassName();
                data[i][1] = dc.getDanceType().getName();
                data[i][2] = dc.getClassDate();
                i++;
            }

            final JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            add(scrollPane);

            JInternalFrame frame = new JInternalFrame("Schedule", true, true, false, false);
            frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            frame.setContentPane(scrollPane);
            frame.pack();
            frame.setVisible(true);
            desktop.add(frame);
        }
    }

    /**
     * Represents action taken when user wants to add a class to the schedule
     */
    private class AddClassAction extends AbstractAction {

        AddClassAction() {
            super("Add Class");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String className = JOptionPane.showInputDialog(null,
                    "Name?",
                    "Enter class name",
                    JOptionPane.QUESTION_MESSAGE);

            String weekday = JOptionPane.showInputDialog(null,
                    "Day of the week?",
                    "Enter class date",
                    JOptionPane.QUESTION_MESSAGE);

            String style = JOptionPane.showInputDialog(null,
                    "Style?",
                    "Enter dance style",
                    JOptionPane.QUESTION_MESSAGE);

            try {
                if (className != null) {
                    DanceClass dc = new DanceClass(className, weekday);
                    dc.setDanceType(new DanceType(style));
                    classList.addClass(dc);
                }
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action taken when user wants to remove a class from the schedule
     */
    private class RemoveClassAction extends AbstractAction {

        RemoveClassAction() {
            super("Remove Class");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String classIndex = JOptionPane.showInputDialog(null,
                    "Which class do you want to remove?",
                    "Enter class number", JOptionPane.QUESTION_MESSAGE);

            try {
                classList.removeClass(Integer.parseInt(classIndex));
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action taken when user wants to enroll a dancer to a class
     */
    private class EnrollInClassAction extends AbstractAction {

        EnrollInClassAction() {
            super("Enroll to");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String classIndex = JOptionPane.showInputDialog(null,
                    "Which class do you want to enroll?",
                    "Enter class number", JOptionPane.QUESTION_MESSAGE);

            String dancerName = JOptionPane.showInputDialog(null,
                    "Who do you want to enroll?",
                    "Enter name", JOptionPane.QUESTION_MESSAGE);

            String dancerAge = JOptionPane.showInputDialog(null,
                    "Age?",
                    "Enter age", JOptionPane.QUESTION_MESSAGE);

            try {
                String name = dancerName;
                int age = Integer.parseInt(dancerAge);
                int i = Integer.parseInt(classIndex);
                DanceClass selectedClass = classList.getClass(i);
                Dancer newDancer = new Dancer(name, age);
                selectedClass.addDancer(newDancer);
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action taken when user wants to save app state
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveSchedule();
            new Image("data/save.png");
            JOptionPane.showMessageDialog(null,"Schedule saved.");
        }
    }

    /**
     * Represents action taken when user wants to load app state
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadSchedule();
            new Image("/Users/joaquingarcia/IdeaProjects/project_n7j0i/data/load.png");
            JOptionPane.showMessageDialog(null,"Schedule loaded.");
        }
    }

}


