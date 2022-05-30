package io.pubby.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import io.pubby.models.AnswerRecord;
import io.pubby.models.Player;
import io.pubby.models.Question;
import io.pubby.models.Session;
import io.pubby.repositories.AnswerRecordRepository;
import io.pubby.repositories.PlayerRepository;
import io.pubby.repositories.QuestionRepository;
import io.pubby.repositories.SessionRepository;

@Service
public class H2DataServiceImpl implements DataService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	AnswerRecordRepository answerRepo;
	
	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	PlayerRepository playerRepo;
	
	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return questionRepo.findAll();
	}

	@Override
	public void saveQuestions(List<Question> questions) {
	
		questionRepo.saveAll(questions);

	}

	@Override
	public void saveSession(Session session) {
		// TODO Auto-generated method stub
		
		sessionRepo.save(session);

	}

	@Override
	public Player getPlayerById(int playerId) {
		// TODO Auto-generated method stub
		return playerRepo.findById(playerId).get();
	}

	@Override
	public List<Player> getPlayersBySessionId(int sessionId) {
	
		Session session = sessionRepo.findById(sessionId).get();
		Player examplePlayer = new Player();
		examplePlayer.setSession(session);
		
		Example<Player> playerExample = Example.of(examplePlayer,ExampleMatcher.matchingAny());
		
		
		
		return playerRepo.findAll(playerExample);
	}

	@Override
	public List<AnswerRecord> getAnswerRecordsByQuestionId(int questionId, int sessionId) {
		
		Question question = questionRepo.findById(questionId).get();
		Session session = sessionRepo.findById(sessionId).get();
		
		
		AnswerRecord exampleAnswerRecord = new AnswerRecord();
		
		exampleAnswerRecord.setSession(session);
		exampleAnswerRecord.setQuestion(question);
		
		Example<AnswerRecord> answerRecordExample = Example.of(exampleAnswerRecord,ExampleMatcher.matchingAny());
		
		
		return answerRepo.findAll(answerRecordExample);
		
	}

	@Override
	public void saveAnswerRecords(List<AnswerRecord> answerRecords) {
		// TODO Auto-generated method stub
		
		answerRepo.saveAll(answerRecords);

	}

	@Override
	public void savePlayer(Player player) {
		
		playerRepo.save(player);
		
	}

	@Override
	public Session getSession(int sessionId) {
		// TODO Auto-generated method stub
		return sessionRepo.findById(sessionId).get();
	}
	

}
