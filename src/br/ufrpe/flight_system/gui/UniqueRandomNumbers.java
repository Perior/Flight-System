package br.ufrpe.flight_system.gui;

import java.util.ArrayList;
import java.util.Collections;

public class UniqueRandomNumbers {
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public UniqueRandomNumbers(int tamanho) {
		for(int i=1; i<=tamanho; i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
	}
	
	public ArrayList<Integer> getList(){
		return list;
	}
}
