package com.vodoo.vodooapiadmin.service;

import com.vodoo.vodooapiadmin.dto.SendmailDto;
import com.vodoo.vodooapiadmin.dto.ModuleResponse;
import com.vodoo.vodooapipersistance.model.Utilisateur;

public interface IMailService {

	  public ModuleResponse sendSimpleMessage(SendmailDto sendmailDto);
	  public ModuleResponse sendSimpleMessageForResetPassword(Utilisateur utilisateur, String token);
}
