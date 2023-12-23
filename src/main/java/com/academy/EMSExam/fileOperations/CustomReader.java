package com.academy.EMSExam.fileOperations;

import java.io.Serializable;
import java.util.List;

public interface CustomReader {
	public List<? extends Serializable> read (String filename);
}
