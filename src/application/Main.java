package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// Import required JavaFX components
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

	// UI components
	ListView<String> listCourses = new ListView<>(); // ListView to display selected courses
	List<String> courses = new ArrayList<>(); // List to store selected courses
	TextArea display = new TextArea(); // TextArea to display student information

	public static void main(String[] args) {
		launch(args); // Launch the JavaFX application
	}

	@Override // Override the start method in the application
	public void start(Stage primaryStage) {
		primaryStage.setTitle("PERSONAL INFORMATION JAVAFX Application"); // Set the stage title

		GridPane gridPane = new GridPane(); // Create a new grid pane to layout the UI
		// Place nodes in the gridPane
		// Padding and gaps for the grid pane
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane.setVgap(5.5);
		gridPane.setHgap(5.5);

		// Personal Information about the student
		// Create text fields for different personal information attributes
		TextField fullNameField = new TextField();
		gridPane.add(new Label("Name:"), 0, 0, 2, 1);
		gridPane.add(fullNameField, 1, 0);

		TextField addressField = new TextField();
		gridPane.add(new Label("Address:"), 0, 1);
		gridPane.add(addressField, 1, 1);

		TextField provinceField = new TextField();
		gridPane.add(new Label("Province:"), 0, 2);
		gridPane.add(provinceField, 1, 2);

		TextField cityField = new TextField();
		gridPane.add(new Label("City:"), 0, 3);
		gridPane.add(cityField, 1, 3);

		TextField postalCodeField = new TextField();
		gridPane.add(new Label("Postal Code:"), 0, 4);
		gridPane.add(postalCodeField, 1, 4);

		TextField phoneNumberField = new TextField();
		gridPane.add(new Label("Phone Number:"), 0, 5);
		gridPane.add(phoneNumberField, 1, 5);

		TextField emailField = new TextField();
		gridPane.add(new Label("Email:"), 0, 6);
		gridPane.add(emailField, 1, 6);

		// Add components as asked in UI
		// Courses depend on the major
		ComboBox<String> coursesComboBox = new ComboBox<>(); // ComboBox to select courses based on the major
		RadioButton computerScience = new RadioButton("Computer Science"); // Radio button for Computer Science major
		RadioButton business = new RadioButton("Business"); // Radio button for Business major
		ToggleGroup group = new ToggleGroup(); // ToggleGroup to ensure only one major can be selected at a time
		computerScience.setToggleGroup(group);
		business.setToggleGroup(group);

		// Set courses based on selected major
		computerScience.setOnAction(e -> coursesComboBox.setItems(FXCollections.observableArrayList("Java", "Python", "C#")));
		business.setOnAction(e -> coursesComboBox.setItems(FXCollections.observableArrayList("Accounting", "Economics", "Math", "Management")));

		// Add selected course to the courses list
		coursesComboBox.setOnAction(e -> {
			String course = coursesComboBox.getValue();

			if (!courses.contains(course) && course != null) {
				courses.add(course);
				listCourses.setItems(FXCollections.observableArrayList(courses));
			}
		});

		// Options checkboxes
		CheckBox studentCouncil = new CheckBox("Student Council"); // Checkbox for Student Council option
		CheckBox volunteerWork = new CheckBox("Volunteer Work"); // Checkbox for Volunteer Work option

		// Displaying information
		Button displayButton = new Button("Display"); // Button to display the student information
		displayButton.setOnAction(e -> {
			StringBuilder studentInfo = new StringBuilder(); // StringBuilder to construct the student information string
			studentInfo.append("Name: ").append(fullNameField.getText()).append("\n");
			studentInfo.append("Address: ").append(addressField.getText()).append(", ");
			studentInfo.append(cityField.getText()).append(", ");
			studentInfo.append(provinceField.getText()).append(", ");
			studentInfo.append(postalCodeField.getText()).append("\n");
			studentInfo.append("Phone: ").append(phoneNumberField.getText()).append("\n");
			studentInfo.append("Email: ").append(emailField.getText()).append("\n");
			studentInfo.append("Major: ").append(computerScience.isSelected() ? "Computer Science" : "Business").append("\n");
			studentInfo.append("Courses: ").append(String.join(", ", courses)).append("\n");
			studentInfo.append("Student Council: ").append(studentCouncil.isSelected() ? "Yes" : "No").append("\n");
			studentInfo.append("Volunteer Work: ").append(volunteerWork.isSelected() ? "Yes" : "No");
			display.setText(studentInfo.toString()); // Set the constructed information to the TextArea for display
		});

		// Checkboxes for the council/volunteer
		gridPane.add(studentCouncil, 3, 1, 2, 1);
		gridPane.add(volunteerWork, 3, 5);

		// Major and courses section
		HBox radioButtons = new HBox(10, computerScience, business); // HBox to hold the major selection radio buttons
		VBox majorCourses = new VBox(10, radioButtons, new Label("Courses"), coursesComboBox, listCourses); // VBox to hold major, courses selection components
		majorCourses.setPadding(new Insets(10));
		gridPane.add(majorCourses, 4, 0, 1, 8);

		// Display button
		gridPane.add(displayButton, 2, 7, 4, 1);
		gridPane.add(display, 0, 8, 6, 2); // TextArea to display student information

		// Create a Scene and place it in the stage
		primaryStage.setScene(new Scene(gridPane, 850, 700)); // Create a new scene with the gridPane layout
		primaryStage.show(); // Display the stage with the UI
	}
}
