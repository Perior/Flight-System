package br.ufrpe.flight_system.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public class RepositorioPassageiros implements IRepositorioPassageiros, Serializable{

	private static final long serialVersionUID = 3486323157461123103L;
	private List<Passageiros> passageiros = null;
	private static RepositorioPassageiros instance;

	//Construtor
	public RepositorioPassageiros(){
		this.passageiros = new ArrayList<>();
	}

	//Singleton
	public static RepositorioPassageiros getInstance() {
		if(instance == null) {
			instance = RepositorioPassageiros.lerArquivo();
		}

		return instance;
	}

	//Cadastrar Passageiro
	public void cadastrar(Passageiros p) throws AlreadyExistException{
		if(this.existe(p)) {
			throw new AlreadyExistException();
		}
		this.passageiros.add(p);
	}

	public boolean existe(Passageiros p) {
		for(int i = 0; i<this.passageiros.size(); i++) {
			if(this.passageiros.get(i).getCpf() == p.getCpf() 
					|| this.passageiros.get(i).getPassaporte() == p.getPassaporte()) {
				return true;
			}
		}
		return false;
	}

	//Listar Passageiros
	public List<Passageiros> listar() {
		return this.passageiros;
	}

	//Editar Passageiro
	public void editar(int index, Passageiros p) {
		this.passageiros.set(index, p);
	}

	//Remover Passageiro
	public void remover(int index) throws RemoveErrorException{
		this.passageiros.remove(index);
	}


	//Arquivos
	private static RepositorioPassageiros lerArquivo(){
		RepositorioPassageiros instanciaLocal = null;

		File arquivo = new File("repositorioPassageiros.dat");

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try{

			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			instanciaLocal = (RepositorioPassageiros) o;

		}catch(Exception e){
			instanciaLocal = new RepositorioPassageiros();

		}finally{
			if(ois != null){
				try{
					ois.close();
				}catch(IOException e){

				}
			}
		}

		return instanciaLocal;

	}

	public void salvarArquivo(){
		if(instance == null){
			return;
		}

		File arquivo = new File("repositorioPassageiros.dat");

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}

			fos = new FileOutputStream(arquivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(oos != null){
				try{
					oos.close();
				}catch(IOException e){

				}
			}
		}
	}

}
