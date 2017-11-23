package be.steformations.it.jacula.interfaces;

import be.steformations.it.jacula.dto.beans.Client;
import be.steformations.it.jacula.dto.beans.File;
import be.steformations.it.jacula.dto.beans.Fileset;
import be.steformations.it.jacula.dto.beans.History;
import be.steformations.it.jacula.dto.beans.Job;
import be.steformations.it.jacula.dto.beans.Schedule;

@javax.jws.WebService
public interface SoapService {

	Client createClient(String ip, String email);
	File createFile(String path);
	Fileset createFileset(String description, int clientId, java.util.List<Integer> fileId);
	Fileset createFilesetOne(String description, int clientId, int fileId);
	History createHistoryJob(int jobId, int status);
	Job createJob(int filesetId, String type, int scheduleId);
	Schedule createSchedule(String start, Integer repeat);
	
}
