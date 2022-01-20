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

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;


public class RepositorioBilhetes implements IRepositorioBilhetes, Serializable{

	private static final long serialVersionUID = -1602985008107051077L;
	private static RepositorioBilhetes instance;
	private List<Bilhete> bilhete;

	//Construtor
	public RepositorioBilhetes(){
		bilhete = new ArrayList<>();
	}

	//Singleton
	public static RepositorioBilhetes getInstance(){
		if(instance == null){
			instance = RepositorioBilhetes.lerArquivo();
		}

		return instance;
	}

	//Criar Bilhete
	public void criar(Passageiros p, Voos v, int numAssento){
		Bilhete ticket = new Bilhete(p, v, numAssento);
		this.bilhete.add(ticket);


	}

	//Buscar Bilhete
	public Bilhete buscar(Object o) {
		Bilhete resultado = null;
		for(int i = 0; i<this.bilhete.size(); i++) {
			if(this.bilhete.get(i).getPassenger().equals(o) || this.bilhete.get(i).getFlight().equals(o)) {
				resultado = this.bilhete.get(i);
			}
		}
		return resultado;
	}

	//Listar Bilhetes
	public List<Bilhete> listar(Voos v) throws BilheteNotExist{
		List<Bilhete> listaVoo = new ArrayList<>();
		for(int i=0; i<this.bilhete.size(); i++) {
			if(this.bilhete.get(i).getFlight().equals(v)) {
				listaVoo.add(this.bilhete.get(i));
			}
		}
		return listaVoo;
	}

	//Arquivos
	private static RepositorioBilhetes lerArquivo(){
		RepositorioBilhetes instanciaLocal = null;

		File arquivo = new File("repositorioBilhetes.dat");

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try{

			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			instanciaLocal = (RepositorioBilhetes) o;

		}catch(Exception e){
			instanciaLocal = new RepositorioBilhetes();

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

		File arquivo = new File("repositorioBilhetes.dat");

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
