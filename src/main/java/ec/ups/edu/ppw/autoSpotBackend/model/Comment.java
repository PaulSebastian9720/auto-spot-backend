package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_COMMENT")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_id")
	private int idComment;

	@Column(name = "com_name", nullable = false)
	private String name;

	@Column(name = "com_mail", nullable = false)

	private String mail;

	@Column(name = "com_comment", nullable = false)
	private String comment;
	
	public Comment() {
	}

	public int getIdComment() {
	    return idComment;
	}

	public void setId(int idComment) {
		this.idComment = idComment;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
