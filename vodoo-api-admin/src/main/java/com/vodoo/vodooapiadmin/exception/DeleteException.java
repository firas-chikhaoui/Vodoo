package com.vodoo.vodooapiadmin.exception;

public class DeleteException extends Exception{

    private String nomEntitie;
    private String raisonException = "Un problème est survenu au cours de la suppression";


    public DeleteException() {
    }

    public DeleteException(String nomEntitie, String raisonException) {
        this.nomEntitie = nomEntitie;
        this.raisonException = raisonException;
    }

    @Override
    public String getMessage(){
        return "L'entité " + nomEntitie + " ne peut pas étre supprimée: " + raisonException;
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

    public void setRaisonDosentExist(){
        raisonException =   "Cette entité n'existe pas dans la base de données";
    }

}
