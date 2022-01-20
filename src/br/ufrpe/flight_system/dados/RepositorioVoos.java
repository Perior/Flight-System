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

import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.EditErrorException;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;

public class RepositorioVoos implements IRepositorioVoos, Serializable{
	
	private static final long serialVersionUID = 6514288358255195783L;
	private List<Voos> voos = null;
	private static RepositorioVoos instance;

	//Construtor
	public RepositorioVoos(){
		this.voos = new ArrayList<>();
	}

	//Singleton
	public static RepositorioVoos getInstance() {
		if(instance == null) {
			instance = RepositorioVoos.lerArquivo();
		}

		return instance;
	}

	//Cadastrar Voo
	public void cadastrar(Voos v){
		this.voos.add(v);
	}

	//Listar Voos
	public List<Voos> listar() {
		return this.voos;
	}

	//Editar Voo
	public void editar(int index, Voos v) throws EditErrorException {
		this.voos.set(index, v);
	}

	//Remover Voo
	public void remover(int index) throws RemoveErrorException {
		this.voos.remove(index);
	}

	//Arquivos
	private static RepositorioVoos lerArquivo(){
		RepositorioVoos instanciaLocal = null;

		File arquivo = new File("repositorioVoos.dat");

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try{

			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			instanciaLocal = (RepositorioVoos) o;

		}catch(Exception e){
			instanciaLocal = new RepositorioVoos();

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

		File arquivo = new File("repositorioVoos.dat");

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
