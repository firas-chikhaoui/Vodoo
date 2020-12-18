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
import com.vodoo.vodooapiadmin.repository.ITokenRepository;
import com.vodoo.vodooapipersistance.model.Token;

@Service
@Transactional
@Primary
public class TokenServiceImpl implements ITokenService {

	@Autowired
	private ITokenRepository tokenRepository;
	private static final String ENTITY_NAME = "Token";

	@Transactional(readOnly = true)
	public Page<Token> findAll(Pageable pageable) {

		return tokenRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Token findOne(String id) throws GetException {

		Optional<Token> token = tokenRepository.findById(id);

		if (!token.isPresent()) {

			GetException getException = new GetException();
			getException.setNomEntitie(ENTITY_NAME);
			getException.setRaisonExceptionNotFound();
			throw getException;

		}

		return token.get();
	}

	@Override
	public Token add(Token token) throws CreateException {

		return tokenRepository.saveAndFlush(token);
	}

	@Override
	public Token update(Token token) throws UpdateException {

		if (token.getIdToken() == null) {

			UpdateException updateException = new UpdateException();
			updateException.setNomEntitie(ENTITY_NAME);
			updateException.setRaisonExceptionIdNull();

			throw updateException;

		}

		return tokenRepository.saveAndFlush(token);
	}

	@Override
	public boolean delete(String id) throws DeleteException {

		if (!tokenRepository.existsById(id)) {
			DeleteException deleteException = new DeleteException();
			deleteException.setNomEntitie(ENTITY_NAME);
			deleteException.setRaisonDosentExist();

			throw deleteException;
		}

		tokenRepository.deleteById(id);
		return !tokenRepository.existsById(id);
	}

}