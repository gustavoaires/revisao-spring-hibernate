package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {

	@Autowired
	private TimeRepositorio repoTime;
	
	@Autowired
	private JogadorRepositorio repoJogador;
	
	public Time getTime(Integer id) {
		return repoTime.findOne(id);
	}
	
	public Time salvarTime(String nome) {
		Time time = new Time();
		time.setNome(nome);
		
		return repoTime.save(time);
	}
	
	public List<Time> getTodosTimes() {
		return repoTime.findAll();
	}
	
	public boolean addJogadorAoTime(Integer idTime, Integer jogadorSemTimeID) {
		Time time = repoTime.findOne(idTime);
		
		if (time.getJogadores().size() == 7) {
			return false;
		} else {
			Jogador jogador = repoJogador.findOne(jogadorSemTimeID);
			
			time.getJogadores().add(jogador);
			jogador.setTime(time);
			
			repoTime.save(time);
			repoJogador.save(jogador);
			return true;
		}
	}
	
	public void delJogadorDoTime(Integer idTime, Integer jogadorDoTime) {
		
		Time time = repoTime.findOne(idTime);
		Jogador jogador = repoJogador.findOne(jogadorDoTime);
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		repoTime.save(time);
		repoJogador.save(jogador);
	}
}
