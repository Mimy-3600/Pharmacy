package back.main.app;

import java.util.ArrayList;
import back.model.Medoc;
import back.repository.MedocRepository;

public class App {
	public static void main(String[] args) {
		ArrayList<Medoc> allMedoc = MedocRepository.getListsMedoc(); 

		for(Medoc medoc : allMedoc) {
			System.out.println(" | " + medoc.getMedocNumber() + " | " + medoc.getMedocDesignation());
		}
	}
}