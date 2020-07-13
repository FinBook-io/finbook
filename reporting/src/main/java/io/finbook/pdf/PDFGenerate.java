package io.finbook.pdf;

import java.io.IOException;
import java.util.Map;

public interface PDFGenerate {

	String PATH = "reporting/src/main/resources/public/finbook/files/temp/";
	String FILE_EXTENSION = ".pdf";

	void create(Map<String, Object> content) throws IOException;

}
