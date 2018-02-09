package cn.li.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "cosdb")

public class Role implements java.io.Serializable {

	// Fields

	private Integer rid;
	private String title;
	private String note;
	private Set<Groups> groupses = new HashSet<Groups>(0);
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String title, String note, Set<Groups> groupses, Set<Member> members) {
		this.title = title;
		this.note = note;
		this.groupses = groupses;
		this.members = members;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "rid", unique = true, nullable = false)

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "note", length = 65535)

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "role_groups", catalog = "cosdb", joinColumns = {
			@JoinColumn(name = "rid", updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "gid", updatable = false) })

	public Set<Groups> getGroupses() {
		return this.groupses;
	}

	public void setGroupses(Set<Groups> groupses) {
		this.groupses = groupses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")

	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

//	@Override
//	public String toString() {
//		return "Role [rid=" + rid + ", title=" + title + ", note=" + note + ", groupses=" + groupses + "]\n";
//	}

	
   
	
}