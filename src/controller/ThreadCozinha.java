package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	Semaphore semaforo;
	private int idPrato;
	private static int posEntrega;
	private static int posSaida;
	private static DecimalFormat df = new DecimalFormat("#.##");

	public ThreadCozinha(Semaphore semaforo, int idPrato) {
		this.semaforo = semaforo;
		this.idPrato = idPrato;
	}

	@Override
	public void run() {
		try {
			cozinhar();
			semaforo.acquire();
			entregar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
			pratoEntregue();
		}
	}

	private void cozinhar() {
		int tempo;
		if (idPrato % 2 == 1) {
			tempo = (int) ((Math.random() * 301) + 500);
		} else {
			tempo = (int) ((Math.random() * 601) + 500);
		}
		double percentual = 0;
		while (percentual < 100) {
			percentual += (100.0 / tempo);
			try {
				sleep(100);
				System.out.println("Prato #" + idPrato + ": percentual de preparo: " + df.format(percentual) + "%.");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Prato #" + idPrato + " finalizado!");
	}

	private void entregar() {
		posSaida++;
		System.err.println("O prato #" + idPrato + " foi o " + posSaida + "º a sair para entrega.");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void pratoEntregue() {
		posEntrega++;
		System.err.println("O prato #" + idPrato + " foi o " + posEntrega + "º prato a ser entregue.");
	}

}
