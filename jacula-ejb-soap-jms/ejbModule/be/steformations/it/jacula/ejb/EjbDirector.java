package be.steformations.it.jacula.ejb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.steformations.it.jacula.dao.jpa.JpaJacula;
import be.steformations.it.jacula.dto.beans.Client;
import be.steformations.it.jacula.dto.beans.File;
import be.steformations.it.jacula.dto.beans.Fileset;
import be.steformations.it.jacula.dto.beans.History;
import be.steformations.it.jacula.dto.beans.Job;
import be.steformations.it.jacula.dto.beans.Schedule;
import be.steformations.it.jacula.jms.JmsToClient;

@javax.ejb.Stateless
public class EjbDirector {

	@PersistenceContext(unitName="jacula")
	private EntityManager em;
	@javax.ejb.EJB
	private JmsToClient jms;
	private JpaJacula jpa;
	
	@javax.annotation.PostConstruct
	private void PostConstruct(){
		System.out.println("EjbJaculaService.PostConstruct()");
		this.jpa = new JpaJacula(this.em);
	}
	
	public Client createClient(String ip, String email){
		System.out.println("EjbJaculaService.createClient()");
		Client client = this.jpa.createClient(ip, email);
		return client;
	}
	
	public File createFile(String path){
		System.out.println("EjbJaculaService.createFile()");
		File file = this.jpa.createFile(path);
		return file;
	}
	
	public Fileset createFileset(String description, int clientId, java.util.List<Integer> filesId){
		System.out.println("EjbJaculaService.createFileset()");
		Fileset fileset = this.jpa.createFileset(description, clientId, filesId);
		return fileset;
	}
	
	public History createHistoryJob(int jobId, int status){
		System.out.println("EjbJaculaService.createHistoryJob()");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		History history = this.jpa.createHistoryJob(jobId, timestamp, status);
		return history;
	}
	
	public Job createJob(int filesetId, String type, int scheduleId){
		System.out.println("EjbJaculaService.createJob()");
		Job job = this.jpa.createJob(filesetId, type, scheduleId);
		this.jms.broacastNewJob(job);
		return job;
	}
	
	public Schedule createSchedule(String start, Integer repeat){
		System.out.println("EjbJaculaService.createSchedule()");
		Schedule schedule = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timestamp = new Date();
		try{
			timestamp = format.parse(start);
			schedule = this.jpa.createSchedule(timestamp, repeat);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return schedule;
	}
	
}
