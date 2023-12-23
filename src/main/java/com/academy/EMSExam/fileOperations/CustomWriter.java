package com.academy.EMSExam.fileOperations;

import java.io.Serializable;
import java.util.List;

public interface CustomWriter {
	void write (List<? extends Serializable> items, String filename);
}
