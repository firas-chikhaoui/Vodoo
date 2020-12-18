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
import com.vodoo.vodooapiadmin.repository.IServiceSplmRepository;
import com.vodoo.vodooapipersistance.model.ServiceSplm;

@Service
@Transactional
@Primary
public class ServiceSplmServiceImpl implements IServiceSplmService {

	@Autowired
	private IServiceSplmRepository serviceSplmRepository;
	private static final String ENTITY_NAME = "ServiceSplm";

	@Transactional(readOnly = true)
	public Page<ServiceSplm> findAll(Pageable pageable) {

		return serviceSplmRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public ServiceSplm findOne(String id) throws GetException {

		Optional<ServiceSplm> serviceSplm = serviceSplmRepository.findById(id);

		if (!serviceSplm.isPresent()) {

			GetException getException = new GetException();
			getException.setNomEntitie(ENTITY_NAME);
			getException.setRaisonExceptionNotFound();
			throw getException;

		}

		return serviceSplm.get();
	}

	@Override
	public ServiceSplm add(ServiceSplm serviceSplm) throws CreateException {

		return serviceSplmRepository.saveAndFlush(serviceSplm);
	}

	@Override
	public ServiceSplm update(ServiceSplm serviceSplm) throws UpdateException {

		if (serviceSplm.getIdService() == null) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionIdNull();

			throw updateException;

		}

		return serviceSplmRepository.saveAndFlush(serviceSplm);
	}

	@Override
	public boolean delete(String id) throws DeleteException {

		if (!serviceSplmRepository.existsById(id)) {
			DeleteException deleteException = new DeleteException();
			deleteException.setNomEntitie(ENTITY_NAME);
			deleteException.setRaisonDosentExist();

			throw deleteException;
		}

		serviceSplmRepository.deleteById(id);
		return !serviceSplmRepository.existsById(id);
	}

}