package cpt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;

public class Main extends Application {
@Override

public void start(Stage stage) {

    XYChart.Series<Number, Number> originalData = new XYChart.Series<>();

    // Create the x-axis for scatter chart
    NumberAxis xAxis = new NumberAxis("Ranking", 0.0d,80.0d, 1.0d );

    // Create the y-axis for scatter chart
    NumberAxis yAxis = new NumberAxis("SPI Rating", 0.0d, 100.0d, 10.0d);

    // Create the scatter chart
    ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
    scatterChart.setTitle("Country SPI Rankings");

    // Create an object of CSVReader
    CSVReader reader = new CSVReader();

    // Use CSVreader to add data to a list
    List<DataBase> dataBaseList = reader.read("src/cpt/spi_global_rankings.csv");

    // Create a set to hold the unique teams
    Set<String> teamsSet = new HashSet<>();
    for (DataBase player : dataBaseList) {
        teamsSet.add(player.getName());
    }

    // Convert the set to a list
    List<String> teamsList = new ArrayList<>(teamsSet);
    // Create a GridPane to hold the checkboxes
    GridPane teamCheckBoxes = new GridPane();
    teamCheckBoxes.setHgap(10);
    teamCheckBoxes.setVgap(10);
    teamCheckBoxes.setPadding(new Insets(10, 10, 10, 10));
    teamCheckBoxes.setMinWidth(300);
    teamCheckBoxes.setMinHeight(100);
    teamCheckBoxes.setAlignment(Pos.CENTER);

    int row = 0;
    int col = 0;
    for (String team : teamsList) {
        CheckBox cb = new CheckBox(team);
        teamCheckBoxes.add(cb, col, row);
        col++;
        if (col == 10) {
            col = 0;
            row++;
        }
    }

    // Create a data series to hold the scatter chart data
    XYChart.Series<Number, Number> data = new XYChart.Series<>();
    // Add data points to the data series
    for (DataBase d : dataBaseList) {
    data.getData().add(new XYChart.Data<>(d.getRank(), d.getSpi()));
    }

    for (DataBase d : dataBaseList) {
        originalData.getData().add(new XYChart.Data<>(d.getRank(), d.getSpi()));
        }

    Button resetBtn = new Button("Reset Data");
    teamCheckBoxes.add(resetBtn, 4, row + 2);

    resetBtn.setOnAction(event -> {
        scatterChart.getData().clear();
    });


    // Add the data series to the scatter chart
    scatterChart.getData().add(data);

    // Add an event handler to each checkbox that updates the scatter plot data when the box is checked or unchecked
    for (Node node : teamCheckBoxes.getChildren()) {
        if (node instanceof CheckBox) {
            CheckBox cb = (CheckBox) node;
            cb.setOnAction(event -> {
                XYChart.Series<Number, Number> filteredData = new XYChart.Series<>();
                for (DataBase d : dataBaseList) {
                    if (cb.isSelected() && cb.getText().equals(d.getName())) {
                        filteredData.getData().add(new XYChart.Data<>(d.getRank(), d.getSpi()));
                    }
                }
                scatterChart.getData().clear();
                scatterChart.getData().add(filteredData);
            });
        }
    }

    // Create the x-axis for bar chart
    CategoryAxis xAxisBar = new CategoryAxis();
    xAxisBar.setLabel("Teams");

    // Create the y-axis for bar chart
    NumberAxis yAxisBar = new NumberAxis();
    yAxisBar.setLabel("Percentage");

    // Create the bar chart
    BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar);
    barChart.setTitle("Team Freethrow Percentage");
    barChart.setPrefHeight(500);

    // Create a map to hold the team percentages
    Map<String, Double> teamPercentages = new HashMap<>();

    for (String team : teamsList) {
        double total = 0;
        int count = 0;
        for (DataBase d : dataBaseList) {
        if (team.equals(d.getName())) {
        total += d.getSpi();
        count++;
        }
        }
        double avg = total / count;
        teamPercentages.put(team, avg);
        }
        
        // Add the data to the bar chart
        for (String team : teamPercentages.keySet()) {
        XYChart.Series<String, Number> teamData = new XYChart.Series<>();
        teamData.setName(team);
        teamData.getData().add(new XYChart.Data<>(team, teamPercentages.get(team)));
        barChart.getData().add(teamData);
        }

        // Add the checkboxes and scatter chart to the grid
        teamCheckBoxes.add(scatterChart, 0, row + 1, 3, 1);
        
        // Create a tab pane to hold the scatter chart and bar chart
        TabPane tabPane = new TabPane();
        Tab scatterTab = new Tab("Scatter Chart", scatterChart);
        Tab barTab = new Tab("Bar Chart", barChart);
        tabPane.getTabs().addAll(scatterTab, barTab);

        // Create a VBox to hold the tab pane and checkboxes
        VBox chartContainer = new VBox();
        chartContainer.getChildren().add(tabPane);
        chartContainer.getChildren().add(teamCheckBoxes);

        // Create the scene
        Scene scene = new Scene(chartContainer, 1200, 1000);

        // Set the stage
        stage.setTitle("NBA Player Free Throw Percentage");
        stage.setScene(scene);
        stage.show();
    }

    // Launch the application
    public static void main(String[] args) {
    launch(args);
    }
}