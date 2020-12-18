package com.vodoo.vodooapiadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vodoo.vodooapiadmin.repository.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.repository.IFonctionRepository;
import com.vodoo.vodooapipersistance.model.Fonction;

@Service
@Transactional
@Primary
public class FonctionServiceImpl implements IFonctionService {

	@Autowired
	private IFonctionRepository fonctionRepository;

	@Autowired
	private IUtilisateurRepository utilisateurRepository;
	private static final String ENTITY_NAME = "Fonction";

	@Transactional(readOnly = true)
	public Page<Fonction> findAll(Pageable pageable) {

		return fonctionRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Fonction findOne(String id) throws GetException {

		Optional<Fonction> fonction = fonctionRepository.findById(id);

		if (!fonction.isPresent()) {

			GetException getException = new GetException();
			getException.setNomEntitie(ENTITY_NAME);
			getException.setRaisonExceptionNotFound();
			throw getException;

		}

		return fonction.get();
	}

	@Override
	public Fonction add(Fonction fonction) throws CreateException {

		if (fonctionRepository.existsByCodeFonc(fonction.getCodeFonc())) {

			CreateException createException = new CreateException();
			createException.setNomEntitie(ENTITY_NAME);
			createException.setRaisonExceptionChampUnique("codeFonc");
			throw createException;
		}

		return fonctionRepository.saveAndFlush(fonction);
	}

	@Override
	public Fonction update(Fonction fonction) throws UpdateException {

		if (fonction.getIdFonc() == null) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionIdNull();

			throw updateException;

		}

		if (fonctionRepository.existsByCodeFoncAndIdFoncNot(fonction.getCodeFonc(), fonction.getIdFonc())) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionChampUnique("codeFonc");

			throw updateException;

		}

		return fonctionRepository.saveAndFlush(fonction);
	}

	@Override
	public boolean delete(String id) throws DeleteException {

		if (!fonctionRepository.existsById(id)) {
			DeleteException deleteException = new DeleteException();
			deleteException.setNomEntitie(ENTITY_NAME);
			deleteException.setRaisonDosentExist();

			throw deleteException;
		}

		fonctionRepository.deleteById(id);
		return !fonctionRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<Fonction> getFonctionsByUser(String idUser)  throws GetException {




		List<Fonction> fonctionsList = new ArrayList<>();



//			if (idUser.length() == 0) {
//				return new Fonction(EnumMessage.USER_FIELD_EMPTY.code);
//			}

		if (!utilisateurRepository.existsByIdUtilisateur(idUser)) {

			fonctionsList=fonctionRepository.findAllByProfilFoncsProfileUserProfilsUtilisateurIdUtilisateurOrderByFonctionDesc(idUser);
		}

		return fonctionsList;
	}

}