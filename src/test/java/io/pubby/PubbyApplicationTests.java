package io.pubby;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.pubby.repositories.PlayerRepository;
import io.pubby.repositories.SessionRepository;
import io.pubby.data.DataService;
import io.pubby.models.AnswerRecord;
import io.pubby.models.Player;
import io.pubby.models.Question;
import io.pubby.models.Session;

@SpringBootTest
class PubbyApplicationTests {

	
	@Autowired
	DataService dataService;
	
	
	
	@Test
	void dataServiceTest() {
		
		Player player1 = new Player("Adrian","Doughboy");
		Player player2 = new Player("Bianca","Secret Bianca");
		
		Question question1 = new Question("CokeOrPepsi","Binary","Coke or Pepsi?");
		Question question2 = new Question("SweetOrSavory","Binary","Sweet or Savory?");
		
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(question1);
		questionList.add(question2);
		
		dataService.saveQuestions(questionList);
		
		Session session = new Session();
		dataService.saveSession(session);
		
		
		player1.setSession(session);
		player2.setSession(session);
		
		dataService.savePlayer(player1);
		dataService.savePlayer(player2);
		
		AnswerRecord answerRecordCokeBianca = new AnswerRecord("Coke",question1,player2,session,false);
		AnswerRecord answerRecordPepsiAdrian = new AnswerRecord("Pepsi",question1,player1,session,false);
		AnswerRecord answerRecordSavoryAdrian = new AnswerRecord("Savory",question2,player1,session,false);
		AnswerRecord answerRecordSavoryBianca = new AnswerRecord("Savory",question2,player2,session,false);
		
		List<AnswerRecord> answerRecordList = new ArrayList<AnswerRecord>();
		answerRecordList.add(answerRecordSavoryBianca);
		answerRecordList.add(answerRecordSavoryAdrian);
		answerRecordList.add(answerRecordPepsiAdrian);
		answerRecordList.add(answerRecordCokeBianca);
		
		
		dataService.saveAnswerRecords(answerRecordList);
		
		
		System.out.println(dataService.getPlayersBySessionId(session.getId()));
		System.out.println(dataService.getQuestions());
		System.out.println(dataService.getAnswerRecordsByQuestionId(1, 1));
		

		
	}

}
