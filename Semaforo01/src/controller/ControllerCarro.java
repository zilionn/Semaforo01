package controller;

import java.util.concurrent.Semaphore;

public class ControllerCarro extends Thread{
	
	private Semaphore semaforo;
	private char sentido;
	private int carroId;
	
	public ControllerCarro(Semaphore semaforo, char sentido, int carroId) {
		this.semaforo = semaforo;
		this.sentido = sentido;
		this.carroId = carroId;
	}
	
	public void run() {
		try {
			semaforo.acquire();
			cruzarCarro();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			chegarCarro();
			semaforo.release();
		}
	}

	private void cruzarCarro() {
		int tempoCruzando = (int)((Math.random() * 2001) + 1000); // 1 - 3 s.
		switch (sentido) {
		case 'N':
			System.out.println("O carro #" + carroId + " está cruzando de sul à norte.");
			break;
		case 'S':
			System.out.println("O carro #" + carroId + " está cruzando de norte à sul.");
			break;
		case 'L':
			System.out.println("O carro #" + carroId + " está cruzando de oeste à leste.");
			break;
		case 'O':
			System.out.println("O carro #" + carroId + " está cruzando de leste à oeste.");
			break;
		}
		try {
			sleep(tempoCruzando);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void chegarCarro() {
		System.out.println("O carro #" + carroId + " passou pelo cruzamento.");
	}



}