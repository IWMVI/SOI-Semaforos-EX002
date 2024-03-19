package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCozinha;

public class Principal {
	public static void main(String[] args) {
		
		int qtdPratos = 5;
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 1; i <= qtdPratos; i++) {
			Thread t = new ThreadCozinha(semaforo, i);
			t.start();
		}
	}
}
