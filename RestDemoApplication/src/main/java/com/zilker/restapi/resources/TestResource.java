package com.zilker.restapi.resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/files")
public class TestResource {
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response fileUpload(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		byte[] bytes = null;
		byte[] encoded = null;
		byte[] decoded = null;
		BufferedImage img = null;
		File file = new File("F:\\lokesh\\test.png");
		try {
		bytes = IOUtils.toByteArray(uploadedInputStream);
		encoded = Base64.getEncoder().encode(bytes);
		FileOutputStream out = new FileOutputStream(new File("F:\\lokesh\\test.txt"));
		out.write(encoded);
		out.flush();
		out.close();
		
		decoded = Base64.getDecoder().decode(encoded);
		FileUtils.writeByteArrayToFile(file, decoded);
		/*img = ImageIO.read(new ByteArrayInputStream(bytes));
		System.out.println("img = " + img.toString());*/
		
		} catch (Exception e) {
			System.out.println("problem while converting data to base64");
		}
		ResponseBuilder response = Response.ok(new ByteArrayInputStream(decoded));
		return response.build();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "hello";
	}
		
	
	
	
	/*String fileLocation = "../" + fileDetail.getFileName();
	StringBuilder stringBuilder = new StringBuilder();
	DataInputStream input = new DataInputStream(uploadedInputStream);
	try {
		while (true) {
			stringBuilder.append(Integer.toBinaryString(input.readByte()));
			System.out.println("\n" + Integer.toBinaryString(input.readByte()));
		}
	} catch (Exception e) {
		System.out.println("Problem in binary convertion");
	}
	try {
		FileOutputStream out = new FileOutputStream(new File(fileLocation));
		int read = 0;
		byte[] bytes = new byte[1024];
		while((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	} catch (Exception e) {
		System.out.println("Problem occurred while executing file upload");
	}
	return stringBuilder.toString();*/
	
}
