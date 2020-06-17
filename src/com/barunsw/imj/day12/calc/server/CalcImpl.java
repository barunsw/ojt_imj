package com.barunsw.imj.day12.calc.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcImpl extends UnicastRemoteObject implements CalcInterface {
	private static Logger LOGGER = LogManager.getLogger(CalcImpl.class);

	protected CalcImpl() throws RemoteException {
		super();
	}

	@Override
	public int add(int a, int b) {
		LOGGER.debug("{} + {} = {}", a, b, a + b);
		return a + b;
	}

	@Override
	public int substract(int a, int b) {
		LOGGER.debug("{} - {} = {}", a, b, a - b);
		return a - b;
	}

	@Override
	public int multiply(int a, int b) {
		LOGGER.debug("{} * {} = {}", a, b, a * b);
		return a * b;
	}

	@Override
	public int devide(int a, int b) {
		LOGGER.debug("{} / {} = {}", a, b, a / b);
		return a / b;
	}
	

}
