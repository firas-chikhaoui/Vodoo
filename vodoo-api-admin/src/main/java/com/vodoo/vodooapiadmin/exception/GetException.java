package com.vodoo.vodooapiadmin.exception;

public class GetException extends Exception{

    private String nomEntitie;
    private String raisonException =  "Un problème est survenu au cours de la récurpération";


    public GetException() {
    }

    public GetException(String nomEntitie, String raisonException) {
        this.nomEntitie = nomEntitie;
        this.raisonException = raisonException;
    }

    @Override
    public String getMessage(){

        return "L'entité " + nomEntitie + " ne peut pas être récupérée " + raisonException;
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

    public void setRaisonExceptionNotFound() {
        this.raisonException = "Entité non trouvée";
    }
}
