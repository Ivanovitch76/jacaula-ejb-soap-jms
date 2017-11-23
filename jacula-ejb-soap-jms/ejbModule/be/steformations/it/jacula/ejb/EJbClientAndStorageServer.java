package be.steformations.it.jacula.ejb;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.steformations.it.jacula.dao.jpa.JpaJacula;
import be.steformations.it.jacula.dto.beans.Job;
import be.steformations.it.jacula.dto.service.ClientAndStorageService;

//@javax.ejb.Stateless
public class EJbClientAndStorageServer implements ClientAndStorageService  {

	@PersistenceContext(unitName="jacula")
	private EntityManager em;

	public String backupsBaseDirectoryPath;
	
	private JpaJacula jpa;
	
	
	public EJbClientAndStorageServer(String backupsBaseDirectoryPath) {
		super();
		this.backupsBaseDirectoryPath = backupsBaseDirectoryPath;
	}
	
	@Override
	public void saveBackup(int clientId, int jobId, String filePath, byte[] fileContent) throws Exception{
		System.out.println("EJbClientAndStorageServer.saveBackup()");
		filePath = filePath.replaceAll(":", "");
		java.nio.file.Path path = java.nio.file.Paths.get(
				backupsBaseDirectoryPath, String.valueOf(clientId), String.valueOf(jobId), filePath);
		java.nio.file.Files.createDirectories(path.getParent());
		java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(fileContent, 0, fileContent.length);
		java.nio.file.Files.copy(in, path);		
	}
	
	@Override
	public byte[] getBackup(Integer clientId, Integer jobId, String filePath) throws Exception{
		System.out.println("EJbClientAndStorageServer.getBackup()");
		filePath = filePath.replaceAll(":", "");
		java.nio.file.Path path = java.nio.file.Paths.get(backupsBaseDirectoryPath, String.valueOf(clientId), String.valueOf(jobId), filePath);
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		java.nio.file.Files.copy(path, out);
		return out.toByteArray();		
	}
	
	@Override
	public Job getJob(Integer jobId){
		System.out.println("EJbClientAndStorageServer.getJob()");
		return this.jpa.getJobById(jobId);
	}
	
	
}
