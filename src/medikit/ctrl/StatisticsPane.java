package medikit.ctrl;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import medikit.controller.Controller;
import medikit.Medikit;
import medikit.misc.WindowStyle;

public class StatisticsPane implements Controller
{
    private static String monthName[] = {"null", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dec"};
    
    @FXML private VBox root;
    @FXML private ComboBox<String> graphicField;
    @FXML private TextField yearField;
    @FXML private Button showButton;
    @FXML private StackPane graphicPane;

    @FXML
    public void initialize()
    {
        yearField.setText("" + LocalDate.now().getYear());
        graphicField.getItems().addAll("Ganancias", "Cantidad de consultas");
        
        showButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) ->
        {
            String graphStyle = graphicField.getValue();
            String yearText = yearField.getText();
            if(!yearText.isEmpty() && !graphStyle.isEmpty())
            {
                int year = Integer.parseInt(yearText);
                String query;
                switch(graphStyle)
                {
                    case "Ganancias":
                    {
                        CategoryAxis xAxis = new CategoryAxis();
                        xAxis.setLabel("Meses");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Importe en pesos (MXN)");
                        
                        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
                        lineChart.setTitle("Ganancias del ano: " + year);
                        lineChart.setAnimated(true);
                        
                        XYChart.Series earnings = new XYChart.Series();
                        earnings.setName("Ganancias");
                        
                        for(int month = 1; month <= 12; month++)
                        {
                            LocalDate date = LocalDate.of(year, month, 1);
                            query = String.format(
                                "SELECT SUM(Appointment.price) " +
                                "	FROM Appointment " +
                                "	WHERE Appointment.date BETWEEN '%d-%d-01' AND '%d-%d-%d';", year, month, year, month, date.lengthOfMonth());
                            ResultSet resultSet = Medikit.executeQuery(query);
                            try
                            {
                                resultSet.next();
                                XYChart.Data<String, Number> item = new XYChart.Data(monthName[month], resultSet.getFloat(1));
                                earnings.getData().add(item);
                            }
                            catch(SQLException e)
                            {
                            }
                        }
                        
                        this.setGraphic(lineChart);
                        lineChart.getData().add(earnings);
                        break;
                    }
                    case "Cantidad de consultas":
                    {
                        CategoryAxis xAxis = new CategoryAxis();
                        xAxis.setLabel("Meses");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Cantidad");
                        
                        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
                        lineChart.setTitle("Cantidad de consultas del ano: " + year);
                        lineChart.setAnimated(true);
                        
                        XYChart.Series earnings = new XYChart.Series();
                        earnings.setName("Consultas");
                        
                        for(int month = 1; month <= 12; month++)
                        {
                            LocalDate date = LocalDate.of(year, month, 1);
                            query = String.format(
                                "SELECT COUNT(Appointment.id) " +
                                "	FROM Appointment " +
                                "	WHERE Appointment.date BETWEEN '%d-%d-01' AND '%d-%d-%d';", year, month, year, month, date.lengthOfMonth());
                            ResultSet resultSet = Medikit.executeQuery(query);
                            try
                            {
                                resultSet.next();
                                earnings.getData().add(new XYChart.Data(monthName[month], resultSet.getInt(1)));
                            }
                            catch(SQLException e)
                            {
                            }
                        }
                        
                        this.setGraphic(lineChart);
                        lineChart.getData().add(earnings);
                        break;
                    }
                }
            }
            else
                Toolkit.getDefaultToolkit().beep();
        });
        
        this.yearField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(!newValue.matches("[0-9]*"))
                yearField.setText(oldValue);
        });
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
            root.requestFocus());
    }
    
    public void initController()
    {
        setGraphic(null);
    }
    
    private void setGraphic(Region chart)
    {
        ObservableList<Node> items = graphicPane.getChildren();
        items.clear();
        if(chart != null)
            items.add(chart);
    }
}