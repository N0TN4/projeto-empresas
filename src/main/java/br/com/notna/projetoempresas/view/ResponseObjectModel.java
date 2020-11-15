package br.com.notna.projetoempresas.view;

import java.util.List;

public class ResponseObjectModel <T>{
	boolean status;
	List<T> data;
	@Override
	public String toString() {
		return "ResponseObjectModel [status=" + status + ", data=" + data + "]";
	}
	
	
	
}
