package idu.cs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import idu.cs.domain.Question;
import idu.cs.domain.User;

@Entity
//Spring이 자동으로 보고 만들어
//필요할 때 new User(); 라고 사용할 수 있도록 해줌
@Table(name = "question")
public class QuestionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	// database에서 sequence number, auto increment => primary key 역할
	private String title;
	

	//외래키 설정
	@ManyToOne
	@JoinColumn(name="fk_question_writer")//외래키 이름
	private UserEntity writer;	
	
	@Lob
	private String contents;
	private LocalDateTime createTime;//자바 팔부터 시간처리하는 기능 추가됨
	
	public Question buildDomain() {
		Question question = new Question();
		question.setId(id);
		question.setTitle(title);
		question.setWriter(writer.buildDomain()); //여기 중요함
		question.setContents(contents);
		question.setCreateTime(createTime);
		return question;		
	}
	
	public void buildEntity(Question question) { //여기도 중요함!!! 매핑매핑
		id = question.getId();
		title = question.getTitle();
		
		UserEntity entity = new UserEntity();
		entity.buildEntity(question.getWriter());
		writer = entity;
		
		contents = question.getContents();
		createTime = question.getCreateTime();
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public UserEntity getWriter() {
		return writer;
	}
	public void setWriter(UserEntity writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createtime) {
		this.createTime = createtime;
	}
}