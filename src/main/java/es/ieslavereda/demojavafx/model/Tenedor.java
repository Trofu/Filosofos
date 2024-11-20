package es.ieslavereda.demojavafx.model;

import javafx.scene.control.Label;

public class Tenedor {
    private int id;
    private Label tenedor;

    public Tenedor(int id, Label tenedor) {
        this.id = id;
        this.tenedor = tenedor;
    }

    public int getId() {
        return id;
    }

    public Label getTenedor() {
        return tenedor;
    }
}
