package com.vodoo.vodooapiadmin.service;

import java.util.Optional;

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
import com.vodoo.vodooapiadmin.repository.IAddonsRepository;
import com.vodoo.vodooapipersistance.model.Addons;

@Service
@Transactional
@Primary
public class AddonsServiceImpl implements IAddonsService {

	@Autowired
	private IAddonsRepository addonsRepository;
	private static final String ENTITY_NAME = "Addons";

	@Transactional(readOnly = true)
	public Page<Addons> findAll(Pageable pageable) {

		return addonsRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Addons findOne(String id) throws GetException {

		Optional<Addons> addons = addonsRepository.findById(id);

		if (!addons.isPresent()) {

			GetException getException = new GetException();
			getException.setNomEntitie(ENTITY_NAME);
			getException.setRaisonExceptionNotFound();
			throw getException;

		}

		return addons.get();
	}

	@Override
	public Addons add(Addons addons) throws CreateException {

		return addonsRepository.saveAndFlush(addons);
	}

	@Override
	public Addons update(Addons addons) throws UpdateException {

		if (addons.getIdAddon() == null) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionIdNull();

			throw updateException;

		}

		return addonsRepository.saveAndFlush(addons);
	}

	@Override
	public boolean delete(String id) throws DeleteException {

		if (!addonsRepository.existsById(id)) {
			DeleteException deleteException = new DeleteException();
			deleteException.setNomEntitie(ENTITY_NAME);
			deleteException.setRaisonDosentExist();

			throw deleteException;
		}

		addonsRepository.deleteById(id);
		return !addonsRepository.existsById(id);
	}

}