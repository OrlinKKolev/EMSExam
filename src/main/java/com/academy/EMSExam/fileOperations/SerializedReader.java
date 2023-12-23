package com.academy.EMSExam.fileOperations;

import com.academy.EMSExam.dto.lineDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedReader implements CustomReader {

	@Override
	public List<? extends Serializable> read(String filename) {
		List<lineDTO> items = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while (true) {
				try {
					lineDTO item = (lineDTO) ois.readObject();
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
