package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("buscar")
public class AmigoInvisibleController {
	
	private List<String> lista = new ArrayList<>();
	
	

	
	public AmigoInvisibleController() {
		super();
		this.lista = Arrays.asList("ricardo","marcelo","martin","jopa","sole","tomomi","mama","papa","sofi");

	}

	@GetMapping("/")
	public ResponseEntity<Object> index(){
		return new ResponseEntity<>("BIENVENIDO",HttpStatus.OK);
	}


	@GetMapping("/{nombre}")
	public ResponseEntity<Object> amigo(@PathVariable("nombre") String nombre){
		
		if(lista.size() == 0) {
			return new ResponseEntity<>("no hay mas gente disponible",HttpStatus.OK);
		}
		return new ResponseEntity<>("tenes que regalarle a: "+encontrarAmigo(lista,nombre),HttpStatus.OK);
		
	}
	
	
	
	public String encontrarAmigo(List<String> laList, String nombre) {
		
	List<String> nuevaList = laList.stream().filter(n -> !nombre.equals(n)).collect(Collectors.toList());
		
		Collections.shuffle(nuevaList);
		
		String nombreADevolver = nuevaList.get(0);
		
		lista = lista.stream().filter(n -> !n.equals(nombreADevolver)).collect(Collectors.toList());
		

		return nombreADevolver;
		
	}
	
	
	
}
