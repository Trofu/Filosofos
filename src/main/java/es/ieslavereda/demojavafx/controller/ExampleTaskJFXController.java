package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class ExampleTaskJFXController {
    @FXML
    public Label Filosofo1;
    public Label Filosofo2;
    public Label Filosofo3;
    public Label Filosofo4;
    public Label Filosofo5;
    public Label Tenedor1;
    public Label Tenedor2;
    public Label Tenedor3;
    public Label Tenedor4;
    public Label Tenedor5;

    private List<Filosofo> filosofos = new ArrayList<>();
    private Thread hiloPrincipal;

    public ExampleTaskJFXController() {
        hiloPrincipal=new Thread();
    }

    // Este método se llamará después de que los elementos @FXML estén inicializados.
    @FXML
    public void initialize() {
        Tenedor t1 = new Tenedor(1,Tenedor1);
        Tenedor t2 = new Tenedor(2,Tenedor2);
        Tenedor t3 = new Tenedor(3,Tenedor3);
        Tenedor t4 = new Tenedor(4,Tenedor4);
        Tenedor t5 = new Tenedor(5,Tenedor5);
        filosofos.add(new Filosofo(Filosofo1, t4, t5));
        filosofos.add(new Filosofo(Filosofo2, t5, t1));
        filosofos.add(new Filosofo(Filosofo3, t1, t2));
        filosofos.add(new Filosofo(Filosofo5, t2, t3));
        filosofos.add(new Filosofo(Filosofo4, t3, t4));
    }

    @FXML
    protected void onHelloButtonClick() {
        try{
            if (!hiloPrincipal.isAlive()){
                hiloPrincipal.start();
                for (Filosofo filosofo : filosofos) {
                    Thread hilo = new Thread(filosofo);
                    hilo.setDaemon(true);
                    hilo.start();
                }
            }
        }catch (Exception e){
            System.out.println("Ya esta iniciado.");
        }
    }
}
