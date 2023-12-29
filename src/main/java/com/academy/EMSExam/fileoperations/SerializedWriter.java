package com.academy.EMSExam.fileoperations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SerializedWriter implements CustomWriter {
	@Override
	public void write(List<? extends Serializable> items, String filename) {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
			 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			for (Serializable item : items) {
				objectOut.writeObject(item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
