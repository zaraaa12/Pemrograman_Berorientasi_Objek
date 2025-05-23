import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;
import java.util.ArrayList;

public class App extends Application {
    private VBox todoBox;
    private TextField inputField;
    private final String FILE_NAME = "todos.txt";
    private ArrayList<ToDoItem> todos = new ArrayList<>();
    private Stage primaryStage;

    static class ToDoItem {
        String text;
        boolean done;

        ToDoItem(String text, boolean done) {
            this.text = text;
            this.done = done;
        }

        @Override
        public String toString() {
            return done + "|" + text;
        }

        static ToDoItem fromString(String line) {
            if (!line.contains("|")) {
                return new ToDoItem(line, false);
            }
            String[] parts = line.split("\\|", 2);
            boolean done = Boolean.parseBoolean(parts[0]);
            String text = parts[1];
            return new ToDoItem(text, done);
        }
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showLoginScreen();
    }

    private void showLoginScreen() {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");

        VBox loginBox = new VBox(10, userLabel, userField, passLabel, passField, loginButton);
        loginBox.setPadding(new Insets(20));
        loginBox.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            if (username.equals("admin") && password.equals("1234")) {
                showMainScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials", ButtonType.OK);
                alert.showAndWait();
            }
        });

        Scene loginScene = new Scene(loginBox, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void showMainScreen() {
        todoBox = new VBox(10);
        todoBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(todoBox);
        scrollPane.setFitToWidth(true);

        inputField = new TextField();
        inputField.setPromptText("Add new task");
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color:rgb(0, 162, 255); -fx-text-fill: white;");
        
        HBox inputPanel = new HBox(10, inputField, addButton);
        inputPanel.setPadding(new Insets(10));

        addButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                todos.add(new ToDoItem(text, false));
                inputField.clear();
                saveTodos();
                updateDisplay();
            }
        });

        loadTodos();
        updateDisplay();

        VBox mainLayout = new VBox(scrollPane, inputPanel);
        Scene scene = new Scene(mainLayout, 500, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To Do List");
        primaryStage.show();
    }

    private void loadTodos() {
        todos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                todos.add(ToDoItem.fromString(line));
            }
        } catch (IOException ignored) {}
    }

    private void saveTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ToDoItem item : todos) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save file", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void updateDisplay() {
        todoBox.getChildren().clear();

        for (int i = 0; i < todos.size(); i++) {
            final int index = i;
            ToDoItem item = todos.get(i);

            CheckBox checkBox = new CheckBox((index + 1) + ". " + item.text);
            checkBox.setSelected(item.done);
            checkBox.setOnAction(e -> {
                todos.get(index).done = checkBox.isSelected();
                saveTodos();
            });

            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            editButton.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog(item.text);
                dialog.setTitle("Edit Task");
                dialog.setHeaderText("Edit your task:");
                dialog.setContentText("Task:");

                dialog.showAndWait().ifPresent(newText -> {
                    if (!newText.trim().isEmpty()) {
                        todos.get(index).text = newText.trim();
                        saveTodos();
                        updateDisplay();
                    }
                });
            });

            deleteButton.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this task?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        todos.remove(index);
                        saveTodos();
                        updateDisplay();
                    }
                });
            });

            HBox buttons = new HBox(5, editButton, deleteButton);
            buttons.setAlignment(Pos.CENTER_RIGHT);

            BorderPane itemPane = new BorderPane();
            itemPane.setLeft(checkBox);
            itemPane.setRight(buttons);
            itemPane.setPadding(new Insets(5));
            itemPane.setStyle("-fx-border-color: lightgray; -fx-background-color:rgb(0, 162, 255);");

            todoBox.getChildren().add(itemPane);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
