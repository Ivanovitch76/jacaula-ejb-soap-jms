package be.steformations.it.jacula.jms;

import be.steformations.it.jacula.dao.work.JmsMessage;
import be.steformations.it.jacula.dto.beans.Job;

@javax.ejb.Singleton
public class JmsToClient {
	
	@javax.annotation.Resource(mappedName="jms/JaculaTopicConnectionFactory")
	private javax.jms.ConnectionFactory factory;
	@javax.annotation.Resource(mappedName="jms/JaculaTopic")
	private javax.jms.Topic topic;
	
	public void broacastNewJob(Job job){
		System.out.println("JmsStorageServer.broacastNewJob()");
		try {
			javax.jms.Connection connection = this.factory.createConnection();
			javax.jms.Session session = connection.createSession();
			javax.jms.MessageProducer producer = session.createProducer(topic);
			JmsMessage jms = new JmsMessage();
			jms.setJobId(job.getId());
			jms.setClientId(job.getFileset().getClient().getId());
			javax.jms.Message message = session.createObjectMessage(jms);
			producer.send(message);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
