package com.academy.EMSExam.fileoperations;

import com.academy.EMSExam.dto.LineDTO;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedReader implements CustomReader {

	@Override
	public List<? extends Serializable> read(String filename) {
		List<LineDTO> items = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while (true) {
				try {
					LineDTO item = (LineDTO) ois.readObject();
					items.add(item);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return items;
	}
}
