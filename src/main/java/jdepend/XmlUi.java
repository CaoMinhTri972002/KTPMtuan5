package jdepend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jdepend.xmlui.JDepend;

public class XmlUi {
	public static void main(String[] args) throws IOException {
		runXmlUi("D:\\Workspace\\Tuan5lab4\\Library-Assistant",  //directory project
				"D:\\Workspace\\Tuan5lab4\\src\\main\\resources\\report.xml", //directory export xml
				"D:\\Workspace\\Tuan5lab4\\jdepend-ui", //directory jdepend ui
				"be"); //directory package
	}

	public static void runXmlUi(String project, String reportXml, String directoryJdependUi, String classPrefix)
			throws IOException {
		JDepend jdpXml = new JDepend(new PrintWriter("D:\\Workspace\\Tuan5lab4\\src\\main\\resources\\report.xml"));
		jdpXml.addDirectory("D:\\Workspace\\Tuan5lab4\\Library-Assistant");
		jdpXml.analyze();
		System.out.println("DONE directory report.xml saved: ");
		System.out.print(reportXml);
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.command("cmd.exe", "/c", "cd " 
		+ directoryJdependUi 
		+ "&& npm run jdepend-ui " 
		+ reportXml 
		+ " "
		+ classPrefix 
		+ " && index.html");

		
		//handle cmd.exe
		try {

			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
