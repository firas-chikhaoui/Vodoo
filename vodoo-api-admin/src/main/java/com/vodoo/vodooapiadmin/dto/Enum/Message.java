package com.vodoo.vodooapiadmin.dto.Enum;

import java.util.HashMap;
import java.util.Map;

public enum Message {
    CREATED_SUCCESS("Object est crée avec succée",1),
    UPDATE_SUCCESS("Object est modifiée avec succée",1),
    DELETE_SUCCESS("Object est supprimer avec succée",1),
    USER_CREATED("l'utilisateur a été crée avec succes", 1),
    USER_NOT_Created("l'utilisateur n'a pas été crée", -1),
    USER_EXIST("Utilisateur existant with Token",2),
    LOGIN_SUCCESS("Utilisateur est authentifié avec succée",1),
    USER_OR_PASSWORD_EMPTY("le champs login ou password est vide", -3),
    USER_NOT_EXIST("utilisateur n'existe pas", -9),
    FORMAT_DATE_INVALIDE("la format de date est invalide", -10),
    USER_IS_NOT_ACTIF("utilisateur n'est pas actif",-88),
    USER_EMPTY_FIELD("le champs  est vide ", -2),
    FACTIF_OBLIGATION("le champs actif doit étre soit 0 soit 1", -4),
    UPDATE_SUCESS("mise à jours terminé avec succée", 1),
    SOME_INPUT_EMPTY("un ou plusieur attribut envoyer sont null",-2),
    INPUT_EMPTY2("l'object envoyer est null",-1),
    ERREUR_QUERY("erreur in query", -1),
    ERRUER_SEND("erreur lors de l'envoie de l'email", -1),
    SUCCESS_SEND("email envoyé avec succés", 1),
    LOGIN_EXIST("login déja existant voulez vous saisir un autre", -6),
    USER_LOGIN("les informations de login sont", 1);

    private static final Map<Integer, Message> BY_CODE = new HashMap<>();
    private static final Map<String, Message> BY_LABEL = new HashMap<>();

    static {
        for (Message e : values()) {
            BY_LABEL.put(e.label, e);
            BY_CODE.put(e.code, e);
        }
    }

    public final String label;
    public final Integer code;

    private Message(String label, Integer code) {
        this.label = label;
        this.code = code;
    }

    public static Message valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Message valueOfCode(Integer number) {
        return BY_CODE.get(number);

    }
    }
