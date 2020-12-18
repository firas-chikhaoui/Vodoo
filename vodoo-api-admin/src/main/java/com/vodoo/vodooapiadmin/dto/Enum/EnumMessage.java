package com.vodoo.vodooapiadmin.dto.Enum;

import java.util.HashMap;
import java.util.Map;


public enum EnumMessage {

    
    CREATED_SUCCESS("Object est crée avec succée",1),
    UPDATE_SUCCESS("Object est modifiée avec succée",1),
    DELETE_SUCCESS("Object est supprimer avec succée",1),
    USER_EXIST("Utilisateur existant with Token",2),
    USER_EXIST2("Utilisateur exist",2),
    ENTREPRISE_DONT_INCLUDE("L'entreprise que vous avez choisie n'appartient pas a la list des entreprise sélectionné",-5),
    USER_EXIST_WITHOUT_TOKEN("Utilisateur existant sans Token Admin",1),
    ERREUR_QUERY("Query failed",-3),
    TOKEN_GENERATED("Obtenir le jeton généré",1),
    TOKEN_NOT_GENERATED("Erreur lors de la jeton génération du token",-1),
    INPUT_EMPTY("l'object envoyer est null",0),
    INPUT_EMPTY2("l'object envoyer est null",-1),
    Email_EMPTY("l'email envoyer est null",-2),
    EMAIL_SENDED("un mail a été envoyé",1),
    SOME_INPUT_EMPTY("un ou plusieur attribut envoyer sont null",-2),
    LIST_PROFIL_FONC("List profilfoncs n'est pas null",1),
    LIST_PROFIL_FONC_EMPTY("List profilfoncs est null",-2),
    User_Empty("Utilisateur envoyé est null",-1),
    Fonc_Empty("la Fonctionaliter envoyé est null",-1),
    ENTREPRISE_Empty("l'Entreprise envoyé est null",-1),
    DIVISION_Empty("la Division envoyé est null",-1),
    DEPARTEMENT_Empty("le departement envoyé est null",-1),
    PROFIL_Empty("le profil envoyé est null",-1),
    LIST_IDPROFIL_EMPTY("La List des Id profil est vide",-2),
    LIST_IDFONC_EMPTY("La List des Id Fonctionaliter est vide",-2),
    LIST_IDENTREPRISE_EMPTY("La List des Id entreprise est vide",-2),
    LIST_IDDEPARTEMENT_EMPTY("La List des Id département est vide",-2),
    LIST_IDDIVISION_EMPTY("La List des Id division est vide",-2),
    USERENTREPRISE_NOT_EMPTY("La liste des Utilisateur affecté à cette entreprise n'est pas vide",1),
    ENTREPRISEUSER_NOT_EMPTY("La liste des Entreprise affecté à cette utilisateur n'est pas vide",1),
    LIST_FONC_NOT_NULL("la list des fonctionaliters n'est pas vide",1),
    LIST_FONC_NULL("la list des fonctionaliters est null",-2),
    USERENTREPRISE_EMPTY("Aucune relation entre les utilisateurs et cette entreprise",-2),
    ACCESSLEVEL("accessLevel est vide",-5),
    ID_EMPTY("l'id envoyé est null",-1),
    ID_ENTREPSIE_SEND_EMPTY("l'id de l'entreprise envoyé est null",-7),
    DEPARTEMENT_EXIST("le departement existe",1),
    USER_NOT_EXIST("Utilisateur n'existe pas",0),
    FONC_NOT_EXIST("la fonctionnalité n'existe pas",0),
    ONE_OR_MANY_FONC_NOT_EXIST("une ou plusieurs fonctionnalité n'existe pas",-4),
    ONE_OR_MANY_PROFIL_NOT_EXIST("une ou plusieurs profils n'existe pas",-4),
    ONE_OR_MANY_ENTREPRISE_NOT_EXIST("une ou plusieurs Entreprise n'existe pas",-4),
    ONE_OR_MANY_DEPARTEMENT_NOT_EXIST("une ou plusieurs departement n'existe pas",-4),
    ONE_OR_MANY_DIVISION_NOT_EXIST("une ou plusieurs Division n'existe pas",-4),
    DIVISION_NOT_EXIST("la Division n'existe pas",0),
    ENTREPRISE_NOT_EXIST("l'Entreprise n'existe pas",0),
    DEPARTEMENT_NOT_EXIST("le departement n'existe pas",0),
    DIVISION_EXIST("la Division existe",1),
    ENTREPRISE_EXIST("l'Entreprise existe",1),
    FONC_EXIST("la fonctionnalité existe",1),
    PROFIL_NOT_EXIST("Profil n'existe pas",0),
    LIST_EMPTY("la list est vide",0),
    LIST_FULL("la liste n'est pas vide ",1),
    USER_FIELD_EMPTY("champs vide",-2),
    User_Updated("l'utilisateur est à jours",1),
    Update_Failed("mise à jours échoué",-1),
    USER_OR_PASSWORD_EMPTY("le champs login ou password est vide",-2),
    FORMAT_DATE_INVALIDE("la format de date est invalide",-10),
    USER_LOGIN("les informations de login sont",1),
    DELETE_FAILED("erreur lors de la suprression",-1),
    UPDATE_SUCESS("mise à jours terminé avec succée",1),
    UPADET_FAILED("erreur lors de la mise à jorus",-1),
    PARTNER_NOT_EXIST("le partenaire est introuvable",-2),
    AFFECT_WITH_SUCCESS("l'affectation terminé avec succée",1),
    AFFECT_FAILED("error lors de l'affectation",-1),
    PROFILE_NOT_EXIST("profil est introuvable",-3),
    SUCCESS_SEND("email is sended with success",1),
    ERRUER_SEND("email is not sended",-1),
    USER_AFFECT_PROFILE("les profils sont affecté avec succées au utilisateur",1),
    USER_AFFECT_PROFILE_FAILED("erreur lors de l'affectations",-1),
    OBJECT_SEND_EMPTY("l'objet envoyé est null",-1),
    TOKEN_NOT_AUTHORIZED2("Cette token n'est pas autoriser a utiliser ces services",-1),
    TOKEN_NOT_AUTHORIZED("access token not authorized",401),
    TOKEN_MISSING("The Bearer Token is missing",402),
    ERREUR_IN_GENEATION("Erreur in generation",-4),
    EMTPY_SNEDED_OBJECT("empty sended object",-2),
    ONEORMANYVALUEAREMISSING("one or many value are empty",-1),
    SUCCESS_GENERATION("token generated with success",1),
    TOKEN_NOT_EXIST("Token not exist ",-2),
    SOME_RESETPASSWORD_VARIABLE_EMPYT("un ou plusieur attribut envoyer sont null",-5),
    TOKEN_NOT_VALID("token n'est pas valide",-2),
    INCORRECT_OLDPASSWORD("mot de passe incorrect",-4),
    RESETPASSWORD_SEND_EMPTY("l'objet Envoyer est null ",-6),
    INVALID_EMAIL_FORMAT("la format du  mail est invalide",-1)
    ;

private static final Map<Integer, EnumMessage> BY_CODE = new HashMap<>();
private static final Map<String, EnumMessage> BY_LABEL = new HashMap<>();

static {
    for (EnumMessage e : values()) {
        BY_LABEL.put(e.label, e);
        BY_CODE.put(e.code, e);
    }
}

public final String label;
public final Integer code;

private EnumMessage(String label, Integer code) {
    this.label = label;
    this.code = code;
}

public static EnumMessage valueOfLabel(String label) {
    return BY_LABEL.get(label);
}

public static EnumMessage valueOfCode(Integer number) {
    return BY_CODE.get(number);
}
}