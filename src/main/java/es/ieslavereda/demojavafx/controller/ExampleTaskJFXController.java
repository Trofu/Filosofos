package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.RegionColor;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ExampleTaskJFXController {
    @FXML
    private Label label;
    private Thread thread;

    public ExampleTaskJFXController(){
        thread = new Thread(new MyTask());
        thread.setDaemon(true);
    }
    @FXML
    protected void onHelloButtonClick() {
        if(!thread.isAlive())
            thread.start();
    }

    private class MyTask extends Task<RegionColor> {

        public MyTask(){
            valueProperty().addListener((observableValue, regionColor, newValue) -> {
                newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(),new CornerRadii(5),new Insets(-5))));
            });
        }
        @Override
        protected RegionColor call() throws Exception {
            while (true){
                updateValue(new RegionColor(label, Color.AQUA));
                Thread.sleep(1000);
                updateValue(new RegionColor(label, Color.RED));
                Thread.sleep(1000);
            }
        }
    }
}