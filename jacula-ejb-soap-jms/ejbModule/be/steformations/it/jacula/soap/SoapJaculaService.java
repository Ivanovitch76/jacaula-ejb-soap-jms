package be.steformations.it.jacula.soap;

import java.util.ArrayList;

import be.steformations.it.jacula.dto.beans.Client;
import be.steformations.it.jacula.dto.beans.File;
import be.steformations.it.jacula.dto.beans.Fileset;
import be.steformations.it.jacula.dto.beans.History;
import be.steformations.it.jacula.dto.beans.Job;
import be.steformations.it.jacula.dto.beans.Schedule;
import be.steformations.it.jacula.ejb.EjbDirector;
import be.steformations.it.jacula.interfaces.SoapService;
@javax.ejb.Singleton
@javax.ejb.Startup
@javax.jws.WebService(
		endpointInterface="be.steformations.it.jacula.interfaces.SoapService",
		targetNamespace="be.steformations.it.jacula.soap",
		serviceName="SoapJaculaService"
		)
public class SoapJaculaService implements SoapService{

	@javax.ejb.EJB
	private EjbDirector ejb;
	
	@Override
	public Client createClient(String ip, String email) {
		System.out.println("SoapJaculaService.createClient()");
		return this.ejb.createClient(ip, email);
	}
	@Override
	public File createFile(String path) {
		System.out.println("SoapJaculaService.createFile()");
		return this.ejb.createFile(path);
	}
	@Override
	public Fileset createFileset(String description, int clientId, java.util.List<Integer> fileId) {
		System.out.println("SoapJaculaService.createFileset()");
		return this.ejb.createFileset(description, clientId, fileId);
	}
	@Override
	public Fileset createFilesetOne(String description, int clientId, int fileId) {
		System.out.println("SoapJaculaService.createFileset()");
		java.util.List<Integer> list = new ArrayList<>();
		list.add(fileId);
		return this.ejb.createFileset(description, clientId, list);
	}
	@Override
	public History createHistoryJob(int jobId, int status) {
		System.out.println("SoapJaculaService.createHistoryJob()");
		return this.ejb.createHistoryJob(jobId, status);
	}
	@Override
	public Job createJob(int filesetId, String type, int scheduleId) {
		System.out.println("SoapJaculaService.createJob()");
		return this.ejb.createJob(filesetId, type, scheduleId);
	}
	@Override
	public Schedule createSchedule(String start, Integer repeat) {
		System.out.println("SoapJaculaService.createSchedule()");
		return this.ejb.createSchedule(start, repeat);
	}

}
