////////////////////////////////////////////////////////////////////
// Riccardo Smanio 1126491
// Davide Sgrazzutti 1127436
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;


public class User {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String CF;
    
    public User(String nome, String cognome, LocalDate dataNascita, String CF)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.CF = CF;
    }

    public String getCodiceFiscale() {
        return this.CF;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public LocalDate getDataNascita() {
        return this.dataNascita;
    }

    public boolean isMaggiorenne() {
        LocalDate currentDate = LocalDate.now();
        if(currentDate.getYear() - this.dataNascita.getYear() == 18 &&
           currentDate.getDayOfYear() <= this.dataNascita.getDayOfYear()){
                return true;
        }
        else if(currentDate.getYear() - this.dataNascita.getYear() > 18){
            return true;
        }
        return false; 

    }
}