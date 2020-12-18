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
import com.vodoo.vodooapiadmin.repository.IPublicationRepository;
import com.vodoo.vodooapipersistance.model.Publication;

@Service
@Transactional
@Primary
public class PublicationServiceImpl implements IPublicationService {

	@Autowired
	private IPublicationRepository publicationRepository;
	private static final String ENTITY_NAME = "Publication";

	@Transactional(readOnly = true)
	public Page<Publication> findAll(Pageable pageable) {

		return publicationRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Publication findOne(String id) throws GetException {

		Optional<Publication> publication = publicationRepository.findById(id);

		if (!publication.isPresent()) {

			GetException getException = new GetException();
			getException.setNomEntitie(ENTITY_NAME);
			getException.setRaisonExceptionNotFound();
			throw getException;

		}

		return publication.get();
	}

	@Override
	public Publication add(Publication publication) throws CreateException {

		return publicationRepository.saveAndFlush(publication);
	}

	@Override
	public Publication update(Publication publication) throws UpdateException {

		if (publication.getIdPublication() == null) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionIdNull();

			throw updateException;

		}

		return publicationRepository.saveAndFlush(publication);
	}

	@Override
	public boolean delete(String id) throws DeleteException {

		if (!publicationRepository.existsById(id)) {
			DeleteException deleteException = new DeleteException();
			deleteException.setNomEntitie(ENTITY_NAME);
			deleteException.setRaisonDosentExist();

			throw deleteException;
		}

		publicationRepository.deleteById(id);
		return !publicationRepository.existsById(id);
	}

}