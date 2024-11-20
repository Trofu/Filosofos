package es.ieslavereda.demojavafx.model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Filosofo implements Runnable {
    private final Label id;
    private final Tenedor tenedor1;
    private final Tenedor tenedor2;

    public Filosofo(Label id, Tenedor tenedor1, Tenedor tenedor2) {
        this.id = id;
        this.tenedor1 = tenedor1;
        this.tenedor2 = tenedor2;
    }


    @Override
    public void run() {
        MyTask myTask = new MyTask();
        Thread taskThread = new Thread(myTask);
        taskThread.start();
    }

    private class MyTask extends Task<RegionColor> {

        public MyTask() {
            valueProperty().addListener((observableValue, oldValue, newValue) -> {
                newValue.getRegion().setBackground(
                        new Background(
                                new BackgroundFill(newValue.getColor(), new CornerRadii(5), new Insets(-5))
                        )
                );
            });
        }

        @Override
        protected RegionColor call() throws Exception{
            boolean start=true;
            while (true) {
                pintar(id,Color.TRANSPARENT);
                if (start){
                    start=false;
                    if (tenedor1.getId()<tenedor2.getId()){
                        cogerIzq();
                    }else {
                        cogerDer();
                    }
                }else {
                    int num = (int) (Math.random() * 2);
                    if (num==1){
                        cogerDer();
                    }else {
                        cogerIzq();
                    }
                }
            }
        }

        private void cogerIzq() throws InterruptedException {
            synchronized (tenedor1) {
                pintar(tenedor1.getTenedor(),Color.RED);
                pintar(id,Color.RED);
                synchronized (tenedor2) {
                    comer();
                    pintar(tenedor2.getTenedor(),Color.TRANSPARENT);
                    pintar(tenedor1.getTenedor(),Color.TRANSPARENT);
                }
            }
            dormir();
        }
        private void  cogerDer() throws InterruptedException {
            synchronized (tenedor2) {
                pintar(tenedor2.getTenedor(),Color.RED);
                pintar(id,Color.RED);
                synchronized (tenedor1) {
                    comer();
                    pintar(tenedor1.getTenedor(),Color.TRANSPARENT);
                    pintar(tenedor2.getTenedor(),Color.TRANSPARENT);
                }
            }
            dormir();
        }
        private void comer() throws InterruptedException {
            pintar(tenedor1.getTenedor(),Color.GREEN);
            pintar(tenedor2.getTenedor(),Color.GREEN);
            updateValue(new RegionColor(id, Color.GREEN));
            Thread.sleep(7000);
        }
        private void dormir() throws InterruptedException {
            updateValue(new RegionColor(id,Color.ORANGE));
            Thread.sleep(5000);
        }
        private void pintar(Label id, Color color) throws InterruptedException {
            updateValue(new RegionColor(id,color));
            Thread.sleep(10);
        }


    }
}
