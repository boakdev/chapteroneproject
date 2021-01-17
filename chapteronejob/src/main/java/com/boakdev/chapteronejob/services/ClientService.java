package com.boakdev.chapteronejob.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boakdev.chapteronejob.dto.ClientDTO;
import com.boakdev.chapteronejob.entities.Client;
import com.boakdev.chapteronejob.repositories.ClientRepository;
import com.boakdev.chapteronejob.services.exceptions.DatabaseException;
import com.boakdev.chapteronejob.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPerPage(PageRequest pageRequest) {
		Page<Client> listEntity = repository.findAll(pageRequest);
		return listEntity.map(e -> new ClientDTO(e));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id " + id + ": Não encontrado!"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public void delete(Long id) {

		try {
			repository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Error database integrity");

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + ": Não encontrado!");
		}

	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
