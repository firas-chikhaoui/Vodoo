package com.vodoo.vodooapiadmin.exception;

public class UpdateException extends Exception{

    private String nomEntitie;
    private String raisonException = "Un problème est survenu au cours de la modification";


    public UpdateException() {
    }

    public UpdateException(String nomEntitie, String raisonException) {
        this.nomEntitie = nomEntitie;
        this.raisonException = raisonException;
    }

    @Override
    public String getMessage(){

        return "L'entité " + nomEntitie + " ne peut pas étre modifiée: " + raisonException;
    }

    public String getNomEntitie() {
        return nomEntitie;
    }

    public void setNomEntitie(String nomEntitie) {
        this.nomEntitie = nomEntitie;
    }

    public String getRaisonException() {
        return raisonException;
    }

    public void setRaisonException(String raisonException) {
        this.raisonException = raisonException;
    }

    public void setRaisonExceptionChampUnique(String attribut) {
        this.raisonException = "La valeur de l'attribut " + attribut + " doit étre unique, mais la valeur saisie existe déjà dans la base de données";
    }

    public void setRaisonExceptionIdNull() {
        this.raisonException =  "L'id ne peut pas étre null";
    }

    public void setRaisonDosentExist() {
        raisonException =   "Cette entité n'existe pas dans la base de données";
    }

}
