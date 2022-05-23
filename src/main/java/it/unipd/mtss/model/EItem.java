////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {

    public enum items { Processor, MotherBoard, Mouse, Keyboard};
    private items categoriaItem;
    private String nome;
    private double prezzo; 

    public EItem(items categoria, String nome, double prezzo) {
        this.categoriaItem = categoria;
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public items getCategoriaItem() {
        return this.categoriaItem;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPrezzo() {
        return this.prezzo;
    }
}