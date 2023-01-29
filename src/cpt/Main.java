package cpt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;

public class Main extends Application {
    // Launch the application
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    
    public void start(Stage stage) {
    
        // x-axis for scatter chart
        NumberAxis xAxis = new NumberAxis("Ranking", 0.0d,81.0d, 1.0d );
    
        // y-axis for scatter chart
        NumberAxis yAxis = new NumberAxis("SPI Rating (Soccer Power Index)", 0.0d, 100.0d, 10.0d);
    
        // Create the scatter chart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
    
        // title for scatter cahart
        scatterChart.setTitle("Country SPI Rankings");{
        scatterChart.setStyle("-fx-font-size: 16;");

        // Create an object of CSVReader
        CSVReader reader = new CSVReader();
    
        // Use CSVreader to add data to the data base list
        List<DataBase> dataBaseList = reader.read("src/cpt/spi_global_rankings.csv");
    
        // Create a set to hold the unique names
        Set<String> namesSet = new HashSet<>();
        for (DataBase player : dataBaseList) {
            namesSet.add(player.getName());
        }
    
        // Convert the set to a list
        List<String> namesList = new ArrayList<>(namesSet);
        // Create a GridPane to hold the checkboxes
        GridPane nameCheckBoxes = new GridPane();
        nameCheckBoxes.setHgap(10);
        nameCheckBoxes.setVgap(10);
        nameCheckBoxes.setPadding(new Insets(10, 10, 10, 10));
        nameCheckBoxes.setMinWidth(300);
        nameCheckBoxes.setMinHeight(100);
        nameCheckBoxes.setAlignment(Pos.CENTER);
    
        int row = 0;
        int col = 0;
        for (String name : namesList) {
            CheckBox cb = new CheckBox(name);
            nameCheckBoxes.add(cb, col, row);
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
        data.setName("Country's Global Rankings vs SPI Rating");
        }
    
        // Add the data series to the scatter chart
        scatterChart.getData().add(data);
        
        
        // Create the x-axis for bar chart
        CategoryAxis xAxisBar = new CategoryAxis();
        xAxisBar.setLabel("Countrys");
    
        // Create the y-axis for bar chart
        NumberAxis yAxisBar = new NumberAxis();
        yAxisBar.setLabel("Ratings");
    
        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar);
        barChart.setTitle("Comparison Between Team's Offensive and Defensive Rating");
        barChart.setStyle("-fx-font-size: 11;");
    
         // Create the first data series to hold the bar chart data
         XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
         // set name for data series 2
         dataSeries2.setName("Offensive Rating"); // appear as legend
         // Add data points to the data series
         for (DataBase d : dataBaseList) {
            dataSeries2.getData().add(new XYChart.Data<>(d.getName(), d.getOff()));
             }

        // create second data series for bar chart
         XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<>();
        // set name for data series 3
         dataSeries3.setName("Defensive Rating"); // appear as legend
         for (DataBase d : dataBaseList) {
            dataSeries3.getData().add(new XYChart.Data<>(d.getName(), d.getDef()));
            }
            
            // add data to bar chart
            barChart.getData().addAll(dataSeries2, dataSeries3);
    
            // set sizing of the barchart
            barChart.setPrefWidth(600);
            barChart.setPrefHeight(500);
    
            // Add the checkboxes and scatter chart to the grid
            nameCheckBoxes.add(scatterChart, 0, row + 1, 3, 1);
    
            // Add an event handler to each checkbox that updates the scatter chart and bar chart data when the box is checked
        for (Node node : nameCheckBoxes.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox cb = (CheckBox) node;
                cb.setOnAction(event -> {
                    XYChart.Series<Number, Number> filteredData = new XYChart.Series<>();
                    XYChart.Series<String, Number> filtereddataSeries2 = new XYChart.Series<>();
                    XYChart.Series<String, Number> filtereddataSeries3 = new XYChart.Series<>();
                    for (DataBase d : dataBaseList) {
                        if (cb.isSelected() && cb.getText().equals(d.getName())) {
                            filteredData.getData().add(new XYChart.Data<>(d.getRank(), d.getSpi()));
                            filtereddataSeries2.getData().add(new XYChart.Data<>(d.getName(), d.getOff()));
                            filtereddataSeries3.getData().add(new XYChart.Data<>(d.getName(), d.getDef()));
                            filteredData.setName(d.getName() + " - Rank vs Soccer Power Index Rating");
                            filtereddataSeries2.setName("Offensive Rating");
                            filtereddataSeries3.setName("Defensive Rating");
                        }
                    }
                    // add filtered data
                    scatterChart.getData().clear();
                    scatterChart.getData().add(filteredData);
                    barChart.getData().clear();
                    barChart.getData().addAll(filtereddataSeries2, filtereddataSeries3);
    
                    // Uncheck all the other checkboxes if another checkbox is checked
                for (Node otherNode : nameCheckBoxes.getChildren()) {
                    if (otherNode instanceof CheckBox) {
                        CheckBox otherCb = (CheckBox) otherNode;
                        if (!otherCb.equals(cb)) {
                            otherCb.setSelected(false);
                        }
                    }
                }
                });
            }
        }   
            // create button to reset data
            Button updateButton = new Button("Reset");
            nameCheckBoxes.add(updateButton, 4, row + 2);
            updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    
            // Clear the current data
            scatterChart.getData().clear();
            barChart.getData().clear();
    
            // Add the original data for scatter chart
            scatterChart.getData().add(data);

            // Create the first data series for the bar chart data
            XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>(); 
            //set name for data series 2
            dataSeries2.setName("Offensive Rating"); // appear as legend
            // Add data points to the data series
            for (DataBase d : dataBaseList) {
                dataSeries2.getData().add(new XYChart.Data<>(d.getName(), d.getOff()));
                }
            // second data series for bar chart
            XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<>();
            // set name for data series 3
            dataSeries3.setName("Defensive Rating"); // appear as legend
            for (DataBase d : dataBaseList) {
                dataSeries3.getData().add(new XYChart.Data<>(d.getName(), d.getDef()));
                }
                // add the data to bar chart
                barChart.getData().addAll(dataSeries2, dataSeries3);
    
    
            // Uncheck all the checkboxes
            for (Node node : nameCheckBoxes.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox cb = (CheckBox) node;
                    cb.setSelected(false);
                }
            }
        }
    });
            // Create a TabPane to hold the tabs for the charts
            TabPane tabPane = new TabPane();
    
            // Create a tab for the scatter chart
            Tab scatterTab = new Tab();
            scatterTab.setText("Scatter Chart");
            scatterTab.setContent(scatterChart);
            tabPane.getTabs().add(scatterTab);
    
            // Create a tab for the bar chart
            Tab barTab = new Tab();
            barTab.setText("Bar Chart");
            barTab.setContent(barChart);
            tabPane.getTabs().add(barTab);

            // Create a VBox to hold the TabPane and the checkboxes
            VBox chartHolder = new VBox();
            chartHolder.getChildren().add(tabPane);
            chartHolder.getChildren().add(nameCheckBoxes);
    
            // Create the scene
            Scene scene = new Scene(chartHolder, 1200, 1200);
    
            // Set the stage
            stage.setTitle("Country's Soccer Power Index Global Ranking");
            stage.setScene(scene);
            stage.show();
        }

    }
}